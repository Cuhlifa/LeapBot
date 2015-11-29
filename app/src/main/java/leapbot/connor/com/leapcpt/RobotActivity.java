package leapbot.connor.com.leapcpt;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class RobotActivity extends Activity implements SensorEventListener {

    static boolean controllerMode= false;

    private static final int SPEECH_REQUEST_CODE = 342234;

    // Create an intent that can start the Speech Recognizer activity
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
// Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    // This callback is invoked when the Speech Recognizer returns.
// This is where you process the intent and extract the speech text from the intent.
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);

            if(spokenText.equalsIgnoreCase("forward")){
                forward();
            }else if(spokenText.equalsIgnoreCase("back")){
                back();
            }else if(spokenText.equalsIgnoreCase("left")){
                left();
            }else if(spokenText.equalsIgnoreCase("right")){
                right();
            }else if(spokenText.equalsIgnoreCase("stop")){
                stop();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot);

        SensorManager m_sensor_manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor m_accelerometer = m_sensor_manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        m_sensor_manager.registerListener(this,m_accelerometer,SensorManager.SENSOR_DELAY_UI);




        final Button voiceButton = (Button) findViewById(R.id.voice);
        voiceButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                displaySpeechRecognizer();

            }
        });

        final ToggleButton controllerButton = (ToggleButton) findViewById(R.id.controller);
        controllerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controllerMode = !controllerMode;
            }
        });

        final Button gameButton = (Button) findViewById(R.id.Games);
        gameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(RobotActivity.this, GameActivity.class));
            }
        });

        final Button settingButton = (Button) findViewById(R.id.Settings);
        settingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(RobotActivity.this, SettingActivity.class));
            }
        });

        final Button forwardButton = (Button) findViewById(R.id.forward);
        forwardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                forward();
            }
        });

        final Button backButton = (Button) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                back();
            }
        });

        final Button rightButton = (Button) findViewById(R.id.right);
        rightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                right();
            }
        });

        final Button leftButton = (Button) findViewById(R.id.left);
        leftButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               left();
            }
        });

        final Button stopButton = (Button) findViewById(R.id.stop);
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stop();
            }
        });

        final Button analogButton = (Button) findViewById(R.id.analog);
        analogButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            startActivity(new Intent(RobotActivity.this, AnalogActivity.class));

            }
        });

    }

    public void forward() {
        if(SettingActivity.bluetoothThread != null){
            byte[] result = new byte[1];
            result[0] = (byte) 1;
            SettingActivity.bluetoothThread.write(result);
        }else{
            Toast.makeText(getApplicationContext(), "Not Connected To Bluetooth",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void right() {
        if(SettingActivity.bluetoothThread != null){
            byte[] result = new byte[1];
            result[0] = (byte) 4;
            SettingActivity.bluetoothThread.write(result);
        }else{
            Toast.makeText(getApplicationContext(), "Not Connected To Bluetooth",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void left() {
        if(SettingActivity.bluetoothThread != null) {
            byte[] result = new byte[1];
            result[0] = (byte) 5;
            SettingActivity.bluetoothThread.write(result);
        }else{
            Toast.makeText(getApplicationContext(), "Not Connected To Bluetooth",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void back() {
        if(SettingActivity.bluetoothThread != null){
            byte[] result = new byte[1];
            result[0] = (byte) 2;
            SettingActivity.bluetoothThread.write(result);
        }else{
            Toast.makeText(getApplicationContext(), "Not Connected To Bluetooth",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void stop() {
        if(SettingActivity.bluetoothThread != null){
            byte[] result = new byte[1];
            result[0] = (byte) 3;
            SettingActivity.bluetoothThread.write(result);
        }else{
            Toast.makeText(getApplicationContext(), "Not Connected To Bluetooth",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(!controllerMode)
            return;

        float axisX = event.values[0];
        float axisY = event.values[1];
        float axisZ = event.values[2];

        NumberFormat rounder = DecimalFormat.getInstance();
        rounder.setMaximumFractionDigits(2);

        if(axisX <= 120){
            left();
        }else if(axisX >= 200){
            right();
        }else if(axisY <= -90){
            back();
        }else if(axisY <= -45){
            stop();
        }else if(axisY <= 0){
            forward();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
