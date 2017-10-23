package com.example.intents_and_intent_filters;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by clive on 28-Jun-14.
 * started by button press in main activity
 * passes on a string extra in the intent
 * broadcast to the receiver
 * www.101apps.co.za
 */
public class MyIntentService extends IntentService {
    private static final String ACTION_SERVICE_WORK
            = "com.example.myintents.intents_app.ACTION_SERVICE_WORK";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String serviceString = intent.getStringExtra("serviceString");
        Intent intentBroadcast = new Intent();
        intentBroadcast.setAction(ACTION_SERVICE_WORK);
        intentBroadcast.putExtra("serviceString", serviceString);
        sendBroadcast(intentBroadcast);
    }
}
