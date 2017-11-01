package com.example.simple_service_part_1_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ForegroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground);
    }

    public void playService(View view) {
        Intent startForegroundIntent = new Intent(MyForegroundService.ACTION_FOREGROUND);
        startForegroundIntent.setClass(ForegroundActivity.this, MyForegroundService.class);
        startService(startForegroundIntent);
    }

    public void stopService(View view) {
        Intent stopIntent = new Intent(ForegroundActivity.this, MyForegroundService.class);
        stopService(stopIntent);
    }

    public void moveForegroundIntoBackground(View view) {
        Intent startBackgroundIntent = new Intent(MyForegroundService.ACTION_BACKGROUND);
        startBackgroundIntent.setClass(ForegroundActivity.this, MyForegroundService.class);
        startService(startBackgroundIntent);
    }
}
