package com.example.intents_and_intent_filters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by clive on 28-Jun-14.
 * receives broadcast intent sent by the service
 * adds a string extra and starts the display activity
 * www.101apps.co.za
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    public MyBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentStartActivity = new Intent(context, DisplayActivity.class);
        intentStartActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intentStartActivity.putExtra("serviceString",
                intent.getStringExtra("serviceString"));
        context.startActivity(intentStartActivity);
    }
}
