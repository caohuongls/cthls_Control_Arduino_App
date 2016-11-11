package cth.cthls;

import android.app.Activity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import java.io.IOException;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import static cth.cthls.MainActivity.*;
import static cth.cthls.R.id.drawer_layout;
import static cth.cthls.R.id.nav_camera;
/*import static cth.cthls.R.id.offled;
import static cth.cthls.R.id.onled;*/


/**
 * Created by caohu_000 on 10/17/2016.
 */

public class control_device extends Activity {
    public static MainActivity.ThreadConnectBTdevice myThreadConnectBTdevice;
    public static MainActivity.ThreadConnected myThreadConnected;
    private Switch ledswitch,fanswitch;
    Button btnSend,onled,offled;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_screen);

        fanswitch = (Switch) findViewById(R.id.fan_switch);
        ledswitch = (Switch) findViewById(R.id.led_switch);



        /*
        onled = (Button)findViewById(R.id.onled);
        offled = (Button)findViewById(R.id.offled);
        onled.setVisibility(View.VISIBLE);
        offled.setVisibility(View.VISIBLE);

        Intent intent = getIntent();

        onled.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (MainActivity.myThreadConnected!=null)
                {
                    byte[] bytesToSend = "a".toString().getBytes();
                    MainActivity.myThreadConnected.write(bytesToSend);
                }
            }
        });

        offled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (MainActivity.myThreadConnected!=null)
                {
                    byte[] bytesToSend = "b".toString().getBytes();
                    MainActivity.myThreadConnected.write(bytesToSend);
                }
            }
        });
*/
    }


    @Override
    public void onStart() {


        //set the switch to ON
        fanswitch.setChecked(true);
        //attach a listener to check for changes in state
        fanswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    if (MainActivity.myThreadConnected!=null)
                    {
                        byte[] bytesToSend = "a".toString().getBytes();
                        MainActivity.myThreadConnected.write(bytesToSend);
                    }
                }
                else
                {
                    if (MainActivity.myThreadConnected!=null)
                    {
                        byte[] bytesToSend = "b".toString().getBytes();
                        MainActivity.myThreadConnected.write(bytesToSend);
                    }

                }

            }
        });



        //set the switch to ON
        ledswitch.setChecked(true);
        //attach a listener to check for changes in state
        ledswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    if (MainActivity.myThreadConnected!=null)
                    {
                        byte[] bytesToSend = "c".toString().getBytes();
                        MainActivity.myThreadConnected.write(bytesToSend);
                    }
                }
                else
                {
                    if (MainActivity.myThreadConnected!=null)
                    {
                        byte[] bytesToSend = "d".toString().getBytes();
                        MainActivity.myThreadConnected.write(bytesToSend);
                    }

                }

            }
        });

        super.onStart();


    }

}
