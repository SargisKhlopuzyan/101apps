package com.example.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private String TAG = "LOG_TAG";
    private static final int NOTIFICATION_ID = 101;
    private static final String NOTIFICATION_CANCEL_ID = "NOTIFICATION_CANCEL_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonShowNotification = (Button) findViewById(R.id.button);
        buttonShowNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                basicCompatNotification();
            }
        });

        Button buttonCancelNotification = (Button) findViewById(R.id.button2);
        buttonCancelNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(NOTIFICATION_ID);
            }
        });
    }

    /*send a basic notification*/
    private void basicCompatNotification() {
        //create the notification builder
        NotificationCompat.Builder basicCompatNotificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CANCEL_ID);
        basicCompatNotificationBuilder
                // the following are required
                .setSmallIcon(R.drawable.battery)
                .setContentTitle("Basic Notification")
                .setContentText("Content Text")
                //these are optional
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setPriority(Notification.PRIORITY_MAX);
        //set up the intent
        Intent intentGoNotificationActivity = new Intent(this, NotificationActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        //add the backstack
        stackBuilder.addParentStack(NotificationActivity.class);
        //add the intent to the top of the stack
        stackBuilder.addNextIntent(intentGoNotificationActivity);
        //get pending intent containing the backstack
        PendingIntent pendingIntentBackStack = stackBuilder.getPendingIntent(100, PendingIntent.FLAG_UPDATE_CURRENT);
        /*NOTE: a content Intent is required for Gingerbread and below otherwise
        throws IllegalArgumentException*/
        basicCompatNotificationBuilder.setContentIntent(pendingIntentBackStack);
        //get a notification manager
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //send the notification
        notificationManager.notify(NOTIFICATION_ID, basicCompatNotificationBuilder.build());
    }
}

