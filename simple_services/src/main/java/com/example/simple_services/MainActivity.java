package com.example.simple_services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static TextView textView;

    private String TAG = "services";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        Button buttonStartSimpleService = (Button) findViewById(R.id.button);
        buttonStartSimpleService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Updating score...");
                Intent startIntent = new Intent(MainActivity.this, MySimpleService.class);
                startIntent.putExtra("userName", "Peter");
                startIntent.putExtra("password", "Pan");
                startIntent.putExtra("score", 10);
                startService(startIntent);
            }
        });

        Button buttonStopSimpleService = (Button) findViewById(R.id.button2);
        buttonStopSimpleService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stopIntent = new Intent(MainActivity.this, MySimpleService.class);
                stopService(stopIntent);
                textView.setText("Service Stopped");
            }
        });

        Button buttonGoForegroundActivity = (Button) findViewById(R.id.button3);
        buttonGoForegroundActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForegroundActivity = new Intent(MainActivity.this, ForegroundActivity.class);
                startActivity(intentForegroundActivity);
            }
        });
    }
}
