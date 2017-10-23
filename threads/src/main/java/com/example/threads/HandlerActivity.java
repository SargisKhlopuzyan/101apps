package com.example.threads;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tutorials.R;

/**
 * Created by clive on 15-Apr-14.
 * demonstrates use of thread and handler
 *
 *      www.101apps.co.za
 */
public class HandlerActivity extends AppCompatActivity {

    private TextView textViewMessage;
    private TextView textViewCounter;

    private int counter;

//    handler receives message from thread on completion of work
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String message = bundle.getString("message");
            textViewMessage.setText(message);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        counter = 0;

        textViewMessage = (TextView) findViewById(R.id.textView);
        textViewCounter = (TextView) findViewById(R.id.textViewCounter);

//        pressing button starts work on a thread. When work finished, it sends a message to the handler
        Button buttonStartThread = (Button) findViewById(R.id.buttonHandler);
        buttonStartThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewMessage.setText("Thread message");

                Runnable aRunnable = new Runnable() {
                    public void run() {
                        goToSleep();
//                        on work finished, send message to handler
                        Message msg = handler.obtainMessage();
                        Bundle bundle = new Bundle();
                        String threadMessage = "It's all over, Bob!";
                        bundle.putString("message", threadMessage);
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                    }
                };

                Thread aThread = new Thread(aRunnable);
                aThread.start();

            }
        });

//        pressing button increases and displays a counter to demonstrate responsiveness while thread busy
        Button buttonCounter = (Button) findViewById(R.id.buttonCounter);
        buttonCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                textViewCounter.setText(String.valueOf(counter));
            }
        });
    }

    //    sleeps for milliseconds
    private void goToSleep() {
        SystemClock.sleep(3000);
    }
}
