package com.example.broadcast_receivers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private String TAG = "junk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "in MainActivity onCreate");

        String message = getIntent().getStringExtra("message");

        if (message != null) {
            Log.i(TAG, "The message is: " + message);
        }

//        starts the second activity to broadcast intent requiring custom permission
        Button buttonGoActivity2 = (Button) findViewById(R.id.button);
        buttonGoActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        //starts the third activity to broadcast an intent using localBroadcastManager
        Button buttonGoActivity3 = (Button) findViewById(R.id.buttonActivity_3);
        buttonGoActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
}
