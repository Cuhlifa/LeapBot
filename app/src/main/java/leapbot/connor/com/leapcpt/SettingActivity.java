package leapbot.connor.com.leapcpt;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class SettingActivity extends Activity {

    String selectedDevice = "";
    static BluetoothThread bluetoothThread = null;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        final ListView listView = (ListView) findViewById(R.id.devices);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item = ((TextView) view).getText().toString();

                if (!item.equalsIgnoreCase("No Bluetooth Devices")) {

                    Toast.makeText(getBaseContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                    selectedDevice = item;

                }


            }

        });

        final Button refreshButton = (Button) findViewById(R.id.game1);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Refreshing...",
                        Toast.LENGTH_SHORT).show();

                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                ArrayList<String> items = new ArrayList<String>();

                if (bluetoothAdapter != null) {

                    if (!bluetoothAdapter.isEnabled()) {
                        bluetoothAdapter.enable();
                    }

                    Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();

                    if (devices != null) {

                        for (BluetoothDevice device : devices) {

                            items.add(device.getAddress());

                        }

                    } else {
                        items.add("No Bluetooth Devices");
                    }


                } else {
                    items.add("No Bluetooth Devices");
                }

                ListView listView = (ListView) findViewById(R.id.devices);

                listView.setAdapter(new ArrayAdapter<String>(listView.getContext(), android.R.layout.simple_list_item_1, items));


            }
        });

        final Button connectButton = (Button) findViewById(R.id.connect);
        connectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Connecting...",
                        Toast.LENGTH_SHORT).show();

                if (selectedDevice == "") {
                    Toast.makeText(getApplicationContext(), "No Device Selected",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

                if (bluetoothAdapter != null) {



                    BluetoothDevice device = bluetoothAdapter.getRemoteDevice(selectedDevice);
                    if (device != null) {
                        bluetoothThread =  new BluetoothThread(device);
                        bluetoothThread.start();
                        TextView status = (TextView) findViewById(R.id.status);
                        status.setText("Connected");
                        Toast.makeText(getApplicationContext(), "Connected",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed",
                                Toast.LENGTH_SHORT).show();
                    }

                }

            }


        });

        final Button controlButton = (Button) findViewById(R.id.Games);
        controlButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, GameActivity.class));
            }
        });

        final Button settingButton = (Button) findViewById(R.id.Controls);
        settingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, RobotActivity.class));
            }
        });

    }

}
