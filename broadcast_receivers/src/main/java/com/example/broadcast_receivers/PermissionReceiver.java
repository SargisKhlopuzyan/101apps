package com.example.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by clive on 06-May-14.
 *
 *  www.idig.za.net
 *
 *  receives broadcast intent sent in SecondActivity
 *
 *  on receipt, starts MySimpleService
 */
public class PermissionReceiver extends BroadcastReceiver {

    private static final String TAG = "LOG_TAG";
    private static final String KEY_SERVICE_TYPE = "type_service";

//    on receipt of broadcast, starts the permission service
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Permission broadcast received");
        Intent intentStartPermissionService = new Intent(context, MySimpleService.class);
        intentStartPermissionService.putExtra(KEY_SERVICE_TYPE, 3);
        context.startService(intentStartPermissionService);
    }
}
