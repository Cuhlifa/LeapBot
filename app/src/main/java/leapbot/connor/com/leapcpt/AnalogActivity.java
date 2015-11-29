package leapbot.connor.com.leapcpt;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AnalogActivity extends Activity {

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final ImageView myImageView = (ImageView) findViewById(R.id.imageView);

        int centerXOnImage= myImageView.getWidth()/2;
        int centerYOnImage= myImageView.getHeight()/2;

        int centerXOfImageOnScreen=myImageView.getLeft()+centerXOnImage;
        int centerYOfImageOnScreen=myImageView.getTop()+centerYOnImage;

        float x =   event.getRawX() - centerXOfImageOnScreen;
        float y =   centerYOfImageOnScreen - event.getRawY()  ;

        double radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

        if(radius < 210){

            stop();

        }else{

            double degrees =     Math.toDegrees(Math.atan2(y,x));
            degrees = degrees < 0 ? degrees + 360 : degrees;

            Log.i("angle", "" + degrees);

            if(degrees > 90 && degrees < 135){
                forward();
            }else if(degrees > 135 && degrees < 225){
                left();
            }else if(degrees > 225 && degrees < 315){
                back();
            }else{
                right();
            }


        }

        return super.onTouchEvent(event);

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analog);

        final Button homeButton = (Button) findViewById(R.id.home);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(AnalogActivity.this, RobotActivity.class));
            }
        });

    }

}
