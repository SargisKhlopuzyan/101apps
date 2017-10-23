package com.example.simple_services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by clive on 18-Apr-14.
 *
 * displays 3 buttons: start foreground service,
 * move foreground service to background/also
 * starts a background service & stop the service
 *
 *  www.101apps.co.za
 */
public class ForegroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground);

        Button buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startForegroundIntent = new Intent(MyForegroundService.ACTION_FOREGROUND);
                startForegroundIntent.setClass(ForegroundActivity.this, MyForegroundService.class);
                startService(startForegroundIntent);
            }
        });

        Button buttonStop = (Button) findViewById(R.id.buttonStop);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stopIntent = new Intent(ForegroundActivity.this, MyForegroundService.class);
                stopService(stopIntent);
            }
        });

        Button buttonMoveServiceBackground = (Button) findViewById(R.id.buttonMoveServiceBackground);
        buttonMoveServiceBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startBackgroundIntent = new Intent(MyForegroundService.ACTION_BACKGROUND);
                startBackgroundIntent.setClass(ForegroundActivity.this, MyForegroundService.class);
                startService(startBackgroundIntent);
            }
        });


    }
}
