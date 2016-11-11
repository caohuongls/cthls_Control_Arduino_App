package cth.cthls;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class welcome_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
        Thread myThreat = new Thread(){


            @Override
            public void run(){
                try{
                    sleep(3000);
                    Intent intent3 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent3);
                    finish();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }; myThreat.start();


    }
}
