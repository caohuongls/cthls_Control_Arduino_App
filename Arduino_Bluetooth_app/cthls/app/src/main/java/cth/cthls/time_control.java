package cth.cthls;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by caohu_000 on 10/18/2016.
 */

public class time_control extends Activity {

    public static MainActivity.ThreadConnectBTdevice myThreadConnectBTdevice;
    public static MainActivity.ThreadConnected myThreadConnected;
    TextView time_value, txtDate,txttimeonled,txttimeoffled,txttimeonfan,txttimeofffan,realtime;
    ImageButton btDate,btTimeonled,btTimeoffled,btTimeonfan,btTimeofffan,btCancelled,btCancelfan;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm");
    SimpleDateFormat sdfdate = new SimpleDateFormat("dd/MM/yyyy");

    String trangthaidulieu = "CoSoDuLieu";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_screen);

        realtime = (TextView) findViewById(R.id.realtime_display);








       /* Goi string theo thoi gian thuc  */
        final Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    String[] splitStr = MainActivity.strReceived.split(",");
                    realtime.setText(splitStr[3]);
                } catch (Exception e1) {
                }
            }
        };

        handler.postDelayed(r, 1000);



                     addControl();
                    addEvent();
    }

    private void addControl() {
        txtDate = (TextView) findViewById(R.id.text_date_on_led);
        btDate = (ImageButton) findViewById(R.id.button_date_on_led);

        txttimeonled = (TextView) findViewById(R.id.text_time_on_led);
        btTimeonled = (ImageButton) findViewById(R.id.button_time_on_led);

        txttimeoffled = (TextView) findViewById(R.id.text_time_off_led);
        btTimeoffled= (ImageButton) findViewById(R.id.button_time_off_led);
         btCancelled = (ImageButton) findViewById(R.id.button_cancel_led);
        calendar =Calendar.getInstance();
        txtDate.setText(sdfdate.format(calendar.getTime()));

    }

    private void addEvent() {
        btDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hienthidatepicker();
            }
        });

        final Button button_realtime = (Button) findViewById(R.id.set_realtime);
        button_realtime.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                TextView realtime_sync_value = (TextView) findViewById(R.id.realtime_sync);
                //  Lấy thời gian theo định dạng sẵn
                SimpleDateFormat sdf = null;
                String strTimeSend = "hh:mm:ss-dd/MM/yyyy";
                sdf = new SimpleDateFormat(strTimeSend);
                String send_realtime = new String(" realtime" + sdf.format(calendar.getTime()));
                realtime_sync_value.setText(" realtime" + sdf.format(calendar.getTime()));

                if (MainActivity.myThreadConnected != null) {
                    byte[] bytesToSend = send_realtime.toString().getBytes();
                    MainActivity.myThreadConnected.write(bytesToSend);
                }

            }
        });






        btTimeonled.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String command_control = " henbatquat";
                String txtsavetime = "SAVE_TIME_ON_LED";
                hienthitimepicker(txttimeonled,command_control,txtsavetime);

            }
        });

        btTimeoffled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String command_control = " hentatquat";
                String txtsavetime = "SAVE_TIME_OFF_LED";
                hienthitimepicker(txttimeoffled,command_control,txtsavetime);

            }
        });







        btCancelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txttimeonled.setText("");
                txttimeoffled.setText("");
                String huyhengio = new String(" huyhengio");

                if (MainActivity.myThreadConnected != null) {
                    byte[] bytesToSend = huyhengio.toString().getBytes();
                    MainActivity.myThreadConnected.write(bytesToSend);
                }
                SharedPreferences preferences = getSharedPreferences(trangthaidulieu,MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("SAVE_TIME_ON_LED","");
                editor.putString("SAVE_TIME_OFF_LED","");
                editor.commit();

            }
        });








    }

    private void hienthitimepicker(final TextView txttimedisplay,final String command_control, final String txtsavetime ) {
        TimePickerDialog.OnTimeSetListener callBack = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                txttimedisplay.setText(sdftime.format(calendar.getTime()));
                String send = new String(command_control +" "+ sdftime.format(calendar.getTime()));
                if (MainActivity.myThreadConnected != null) {
                    byte[] bytesToSend = send.toString().getBytes();
                    MainActivity.myThreadConnected.write(bytesToSend);
                }

                String savetime = new String(sdftime.format(calendar.getTime()));
                SharedPreferences preferences = getSharedPreferences(trangthaidulieu,MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(txtsavetime,savetime);
                editor.commit();
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                time_control.this,
                callBack,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true);
        timePickerDialog.show();
    }

    private void hienthidatepicker() {

        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                txtDate.setText(sdfdate.format(calendar.getTime()));

            }
        };
        DatePickerDialog datepickerdialog = new DatePickerDialog(
                time_control.this,
                callBack,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        datepickerdialog.show();

    }


    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences preferences = getSharedPreferences(trangthaidulieu,MODE_PRIVATE);
        txttimeonled.setText(preferences.getString("SAVE_TIME_ON_LED",""));
        txttimeoffled.setText(preferences.getString("SAVE_TIME_OFF_LED",""));
        addControl();
        addEvent();
        try {
            String[] splitStr = MainActivity.strReceived.split(",");
            realtime.setText(splitStr[3]);
        } catch (Exception e1) {
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        addControl();
        addEvent();

        SharedPreferences preferences = getSharedPreferences(trangthaidulieu,MODE_PRIVATE);
        txttimeonled.setText(preferences.getString("SAVE_TIME_ON_LED",""));
        txttimeoffled.setText(preferences.getString("SAVE_TIME_OFF_LED",""));
        try {
            String[] splitStr = MainActivity.strReceived.split(",");
            realtime.setText(splitStr[3]);




        } catch (Exception e1) {
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }


}