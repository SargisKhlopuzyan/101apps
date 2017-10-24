package com.example.broadcast_receivers;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by clive on 05-May-14.
 *
 *  www.idig.za.net
 *
 *  registers/unregisters permission permissionReceiver in code
 *  broadcasts an intent which includes a required permission to be received
 *
 *  need to include permission and uses-permission in manifest
 *
 */
public class SecondActivity extends Activity {

    private static final String TAG = "LOG_TAG";
    private static final String ACTION_PERMISSION_TEST = "com.example.broadcast_receivers.broadcast.ACTION_PERMISSION_TEST";
    private String requiredPermission = "com.example.broadcast_receivers.broadcast.PERMISSION_FROM_ME";

    private IntentFilter filter;
    private PermissionReceiver permissionReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

//        create permissionReceiver and filter
        permissionReceiver = new PermissionReceiver();
        filter = new IntentFilter(ACTION_PERMISSION_TEST);

//        send the broadcast
        Button buttonStartPermissionService = (Button) findViewById(R.id.buttonPermission);
        buttonStartPermissionService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Sending permission broadcast");
                Intent intentBroadcastPermission = new Intent(ACTION_PERMISSION_TEST);
                sendBroadcast(intentBroadcastPermission, requiredPermission);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Registering permission permissionReceiver");
        registerReceiver(permissionReceiver, filter, requiredPermission, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Unregistering permission permissionReceiver");
        unregisterReceiver(permissionReceiver);
    }
}
