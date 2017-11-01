package com.example.simple_service_part_1_2;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

public class MySimpleService extends Service {

    private Thread backgroundServiceThread;

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String message = bundle.getString("message");
            MainActivity.textView.setText(message);

        }
    };

    public MySimpleService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("LOG_TAG_TAG", "MySimpleService: onStartCommand");
        switch (flags) {

            case START_FLAG_REDELIVERY:
                Log.e("LOG_TAG", "START_FLAG_REDELIVERY: reusing original intent in Service restart");
                /*reusing original intent to restart after system killed service*/
                break;
            case START_FLAG_RETRY:
                Log.e("LOG_TAG", "START_FLAG_RETRY: retrying original intent in Service as first attempt failed");
                /*the Service has been restarted after an abnormal termination or failed start*/
                break;
            default://value of 0
                Log.i("LOG_TAG", "DEFAULT: default flag used");
                break;
        }

        Log.e("LOG_TAG", "Service startId: " + String.valueOf(startId));
        Toast.makeText(this, "Service created... " + startId, Toast.LENGTH_SHORT).show();
        startBackgroundThread(intent, startId);
        /*only restart if there are pending start calls/
        or killed before stopSelf called
        - it will use the original intent in restarting*/
        return START_REDELIVER_INTENT;
    }

    private void startBackgroundThread(Intent intent, final int startId) {
        final String userName = intent.getStringExtra("userName");
        final String password = intent.getStringExtra("password");
        final int score = intent.getIntExtra("score", 0);

        backgroundServiceThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("LOG_TAG", "Updating the score...");
                SystemClock.sleep(4000);
                Log.e("LOG_TAG", "Update successful");

                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString("message", "Score updated: " + startId);
                message.setData(bundle);
                Log.i("LOG_TAG", "Sending message to handler...");
                handler.sendMessage(message);

                Log.i("LOG_TAG", "Service stopping self ID: " + startId);
                stopSelf(startId);
            }
        });
        backgroundServiceThread.setPriority(Process.THREAD_PRIORITY_BACKGROUND);
        backgroundServiceThread.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("LOG_TAG_TAG", "Destroying Service...");
        Toast.makeText(this, "Destroying Service ...", Toast.LENGTH_SHORT).show();
//      kill the thread
        if (backgroundServiceThread != null) {
            Log.e("LOG_TAG_TAG", "Destroying Thread...");
            Thread dummy = backgroundServiceThread;
            backgroundServiceThread = null;
            dummy.interrupt();
        }
    }
}