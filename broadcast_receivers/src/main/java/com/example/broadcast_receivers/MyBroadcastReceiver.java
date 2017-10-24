package com.example.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by clive on 01-May-14.
 * <p/>
 * www.idig.za.net
 * <p/>
 * receives broadcast intent broadcasted by the system (when power/usb cable plugged in
 * <p/>
 * receives broadcast even if app not running as the broadcast receiver is registered in the manifest
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    private String TAG = "LOG_TAG";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Received system broadcast");
        Intent intentStartMainActivity = new Intent(context, MainActivity.class);
        intentStartMainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentStartMainActivity);
    }
}
