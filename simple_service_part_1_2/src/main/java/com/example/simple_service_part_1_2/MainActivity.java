package com.example.simple_service_part_1_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
    }

    public void startService(View view) {
        textView.setText("Updating score...");
        Intent startIntent = new Intent(MainActivity.this, MySimpleService.class);
        startIntent.putExtra("userName", "Peter");
        startIntent.putExtra("password", "Pan");
        startIntent.putExtra("score", 10);
        startService(startIntent);
    }

    public void stopService(View view) {
        Intent stopIntent = new Intent(MainActivity.this, MySimpleService.class);
        stopService(stopIntent);
        textView.setText("Service Stopped");
    }

    public void foregroundActivity(View view) {
        Intent intentForegroundActivity = new Intent(MainActivity.this, ForegroundActivity.class);
        startActivity(intentForegroundActivity);
    }
}
