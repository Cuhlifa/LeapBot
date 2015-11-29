package leapbot.connor.com.leapcpt;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by fsfdsdf on 11/19/2015.
 */
public class BluetoothThread extends  Thread{

    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    byte[] message;
    byte[] lastMessage;

    public BluetoothThread(BluetoothDevice device) {
        // Use a temporary object that is later assigned to mmSocket,
        // because mmSocket is final
        BluetoothSocket tmp = null;
        mmDevice = device;

        // Get a BluetoothSocket to connect with the given BluetoothDevice
        try {
            // MY_UUID is the app's UUID string, also used by the server code
            tmp = device.createRfcommSocketToServiceRecord(mmDevice.getUuids()[0].getUuid());
        } catch (IOException e) { }
        mmSocket = tmp;
    }

    @Override
    public void run() {

        try {
            // Connect the device through the socket. This will block
            // until it succeeds or throws an exception
            mmSocket.connect();
        } catch (IOException connectException) {
            // Unable to connect; close the socket and get out
            try {
                mmSocket.close();
            } catch (IOException closeException) { }
            return;
        }

        byte[] buffer = new byte[1024];  // buffer store for the stream
        int bytes; // bytes returned from read()

        // Keep listening to the InputStream until an exception occurs
        while (true) {
            try {
                // Read from the InputStream

                if(!mmSocket.isConnected()){

                    cancel();

                }

                if(message != null && message != lastMessage){

                    BufferedOutputStream bos = new BufferedOutputStream(mmSocket.getOutputStream());
                    bos.write(message);
                    lastMessage = message;
                    bos.flush();

                }



                // Send the obtained bytes to the UI activity
                sleep(10);
            } catch (IOException e) {
                try {
                    SettingActivity.bluetoothThread = null;
                    mmSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /* Call this from the main activity to send data to the remote device */
    public void write(byte[] bytes) {
            message = bytes;
    }

    public void cancel() {
        try {
            SettingActivity.bluetoothThread = null;
            mmSocket.close();
        } catch (IOException e) { }
    }
}
