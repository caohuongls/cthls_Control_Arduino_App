package cth.cthls;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cth.cthls.R;
import cth.cthls.MainActivity;

public class sensor_menu extends Activity {

    public static MainActivity.ThreadConnectBTdevice myThreadConnectBTdevice;
    public static MainActivity.ThreadConnected myThreadConnected;
    TextView valuess,light,temp,humidity,time,move;
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_screen);

        Intent intent = getIntent();
        /* Ma code demo chuyen string sang activity khac, string khong chay theo thoi gian thuc :  str = intent.getStringExtra("data");*/
        valuess = (TextView) findViewById(R.id.sensor_value);
        light= (TextView) findViewById(R.id.light_value);
        temp= (TextView) findViewById(R.id.temp_value);
        humidity= (TextView) findViewById(R.id.humidity_value);
        time= (TextView) findViewById(R.id.time_value);
        move= (TextView) findViewById(R.id.move_value);

/*    Goi string theo thoi gian thuc  */
       final  Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                handler.postDelayed(this, 1000);
                valuess.setText(MainActivity.strReceived );

                try {
                    String[] splitStr = MainActivity.strReceived.split(",");
                    light.setText(splitStr[0]);
                    temp.setText(splitStr[1]);
                    humidity.setText(splitStr[2]);
                    time.setText(splitStr[3]);
                    move.setText(splitStr[4]);
                } catch (Exception e1) {

                }
            }
        };

        handler.postDelayed(r, 1000);

    }


    @Override
     public void onStart() {
        super.onStart();
        valuess.setText(MainActivity.strReceived);

        try {
            String[] splitStr = MainActivity.strReceived.split(",");
            light.setText(splitStr[0]);
            temp.setText(splitStr[1]);
            humidity.setText(splitStr[2]);
            time.setText(splitStr[3]);
            move.setText(splitStr[4]);
        } catch (Exception e1) {
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        valuess.setText(MainActivity.strReceived);

        try {
            String[] splitStr = MainActivity.strReceived.split(",");
            light.setText(splitStr[0]);
            temp.setText(splitStr[1]);
            humidity.setText(splitStr[2]);
            time.setText(splitStr[3]);
            move.setText(splitStr[4]);
        } catch (Exception e1) {

        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}



