package com.example.broadcast_receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by clive on 05-May-14.
 * <p/>
 * www.idig.za.net
 * <p/>
 * registers a local broadcast receiver
 * sends a local broadcast intent
 * receives the local broadcast intent and starts MySimpleService
 */
public class ThirdActivity extends Activity {

    private String TAG = "LOG_TAG";
    private static final String KEY_SERVICE_TYPE = "type_service";
    private static final String ACTION_LOCAL_BROADCAST = "com.example.broadcast_receivers.broadcast.ACTION_LOCAL_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_third);

        LocalBroadcastManager.getInstance(this).registerReceiver(myLocalReceiver, new IntentFilter(ACTION_LOCAL_BROADCAST));

//        button starts the local broadcaster
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACTION_LOCAL_BROADCAST);
                // include some extra data.
                intent.putExtra("message", "Local broadcast received");
                LocalBroadcastManager.getInstance(ThirdActivity.this).sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myLocalReceiver);
    }

    private BroadcastReceiver myLocalReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("message");
            Log.i(TAG, "Got message: " + message);
            Intent intentStartService = new Intent(context, MySimpleService.class);
            intentStartService.putExtra(KEY_SERVICE_TYPE, 2);
            context.startService(intentStartService);
        }
    };
}
