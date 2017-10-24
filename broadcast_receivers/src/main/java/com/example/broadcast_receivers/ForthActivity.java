package com.example.broadcast_receivers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by clive on 05-May-14.
 * <p/>
 * www.idig.za.net
 * <p/>
 * started by MySimpleService when thread finished
 */
public class ForthActivity extends Activity {

    private static final String TAG = "LOG_TAG";
    private static final String KEY_SERVICE_TYPE = "type_service";
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "starting ForthActivity...");

        int serviceType = getIntent().getIntExtra(KEY_SERVICE_TYPE, -1);
        switch (serviceType) {
            case -1:
                Log.i(TAG, "Service finished - You have a problem!");
                message = "There is a problem!";
                break;
            case 0:
//                unused
                break;
            case 1:
//                unused
                break;
            case 2:
//                coming from ThirdActivity - local broadcast
                Log.i(TAG, "Service finished - Local Broadcast");
                //stop the service
                Intent stopLocalService = new Intent(ForthActivity.this, MySimpleService.class);
                stopService(stopLocalService);
                message = "Local Broadcast intent received and processed";
                break;
            case 3:
//                coming from SecondActivity - permission broadcast
                Log.i(TAG, "Service finished - Permission Broadcast");
                //stop the service
                Intent stopPermissionService = new Intent(ForthActivity.this, MySimpleService.class);
                stopService(stopPermissionService);
                message = "Permission Broadcast intent received and processed";
                break;
        }

//        load up some data and start the  main activity
        Intent startMainActivityIntent = new Intent(ForthActivity.this, MainActivity.class);
        startMainActivityIntent.putExtra("message", message);
        startActivity(startMainActivityIntent);
    }
}
