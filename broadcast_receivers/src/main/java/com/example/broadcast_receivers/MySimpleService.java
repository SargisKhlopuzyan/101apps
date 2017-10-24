package com.example.broadcast_receivers;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by clive on 05-May-14.
 *
 *  www.idig.za.net
 *
 *  started by local broadcast manager's onReceive and also by the PermissionReceiver's onReceive
 *  does some work in a separate thread and then when finished, starts the forth activity
 *
 *  passes the KEY_SERVICE_TYPE integer to indicate which broadcast receiver started the service
 */
public class MySimpleService extends Service {

    private String TAG = "LOG_TAG";
    private static final String KEY_SERVICE_TYPE = "type_service";

    private Thread backgroundThread;
    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        final int serviceType = intent.getIntExtra(KEY_SERVICE_TYPE, -1);

//        backgroundThread runs in the service. sleeps for 2 seconds then starts the 4th activity
        backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {

                Log.i(TAG, "Simple Service thread started, sleeping for 2 seconds...");
                SystemClock.sleep(2000);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "About to start the Forth Activity");
                        Intent intentStartForthActivity = new Intent(MySimpleService.this, ForthActivity.class);
                        intentStartForthActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intentStartForthActivity.putExtra(KEY_SERVICE_TYPE, serviceType);
                        startActivity(intentStartForthActivity);
                    }
                });
            }
        });
        backgroundThread.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (backgroundThread != null) {
            Thread dummy = backgroundThread;
            backgroundThread = null;
            dummy.interrupt();
        }
        Log.i(TAG, "Simple Service destroyed");
    }
}
