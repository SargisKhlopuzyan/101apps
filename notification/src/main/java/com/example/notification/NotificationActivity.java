package com.example.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ToggleButton;

/**
 * Created by clive on 2/7/14.
 */
public class NotificationActivity extends Activity {

    private static final int REQUEST_CODE = 10;
    private static final int NOTIFICATION_ID = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    public void onToggleClicked(View view) {
        boolean on = ((ToggleButton) view).isChecked();

        if (on) {
            //cancel notification
            NotificationManager theNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            theNotificationManager.cancel(NOTIFICATION_ID);
        } else {
            //send notification
            sendNotification();
        }
    }

    private void sendNotification() {
        //get large icon
        Bitmap largeIconBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.large_icon);
        //get the big picture
        Bitmap myBigPicture = BitmapFactory.decodeResource(this.getResources(), R.drawable.big_picture);
        //build the basic notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setAutoCancel(true)
                .setSmallIcon(R.drawable.airplane)
                .setContentTitle("The Content Title")
                .setContentText("The Content Text message")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        //add stuff for the big picture notification
        builder.setLargeIcon(largeIconBitmap)
                //needs api 11 to show
                .setContentInfo("The content info")
                .setTicker("The ticker text");

        //set up the intent
        Intent intentGoMainActivity = new Intent(this, MainActivity.class);
        /*set the flag - clears top of stack of any activities,
        putting new activity on top if it is running, else starts it*/
        intentGoMainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntentGoMain = PendingIntent.getActivity(this, REQUEST_CODE, intentGoMainActivity,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntentGoMain);

        //build the big picture notification
        NotificationCompat.BigPictureStyle bigPicNote = new NotificationCompat.BigPictureStyle();
        bigPicNote.bigPicture(myBigPicture)
                .setBigContentTitle("Big Picture Content Title")
                .setSummaryText("Big Picture summary text").build();
        Notification compatNotification = builder.setStyle(bigPicNote).build();
        //get the notification manager
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //send the notification
        notificationManager.notify(NOTIFICATION_ID, compatNotification);
    }
}


