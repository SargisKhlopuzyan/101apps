package com.example.intents_and_intent_filters;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

/*displays a number of buttons
www.101apps.co.za*/
public class MainActivity extends Activity{

    private static final int REQUEST_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*use to display a chooser - will display list of
    all appropriate apps - shows no-app dialog if none*/
    public void sendEmail(View view){
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("message/rfc822");
        sendIntent.putExtra(Intent.EXTRA_EMAIL,
                new String[]{"harry@here.com"});
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Happy Harry");
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Dear John, how are you, blah, blah, blah...");
        startActivity(Intent.createChooser(sendIntent, "Choose mail app..."));
    }

    /*start a new activity, passing an extra*/
    public void startNewActivity(View view) {
        Intent intentNewActivity = new Intent(this,
                MyActionSendActivity.class);
        intentNewActivity.putExtra("whichIntent", 1);
        startActivity(intentNewActivity);
    }

/*    starts dialer activity to make call - dummy phone number*/
    public void makePhoneCall(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:000-123-1234"));
        PackageManager packageManager = getPackageManager();
        ComponentName componentName = intent.resolveActivity(packageManager);
        if (componentName != null) {
            startActivity(intent);
        } else {
           /* no activity available to perform this action - does not display no-app dialog
            so you need to handle this yourself*/
            Toast.makeText(this, "No activity available for request",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /*starts an activity in the systems contacts app to select contact from contact list
    * the selected contact is returned/or not*/
    public void pickContact(View view){
        Uri uri = Uri.parse("content://contacts");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent, REQUEST_CODE);
    }

    /*starts a service*/
    public void startMyService(View view) {
        Intent intentStartService = new Intent(this, MyIntentService.class);
        intentStartService.putExtra("serviceString",
                "String extra passed via an intent");
        startService(intentStartService);
    }

    /*gets the returned contact that we selected */
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Toast.makeText(this,"Selected contact received",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
