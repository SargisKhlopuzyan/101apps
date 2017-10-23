package com.example.intents_and_intent_filters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by clive on 27-Jun-14.
 * started by button press in main activity. uses received extra
 * in switch statement to display a text string.
 * also displays a button to start activity for result.
 * the received result is displayed as text string
 * www.101apps.co.za
 */
public class MyActionSendActivity extends Activity {

    private static final int REQUEST_CODE = 101;
    private static final String TAG = "junk";
    private int whichIntent;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_send);

        //**************************
        Intent intent = getIntent();
        String action = intent.getAction();
        String dataType = intent.getType();
        Log.i(TAG, "Action: " + action);
        Log.i(TAG, "Mime type: : " + dataType);
        //**************************

        textView = (TextView) findViewById(R.id.textView);
        String displayText = null;
        whichIntent = getIntent().getIntExtra("whichIntent", -1);
        switch (whichIntent) {
            case 1:
        /*executed if whichIntent received from button Press in main activity
                    to start new activity*/
                displayText = "Started by startActivity()";
                break;
            default:
                /*will display if started by the selection of this app's icon from the send email dialog*/
                displayText = "You're in MyActionSendActivity";
                break;
        }
        textView.setText(displayText);
    }

    /*start a new activity to get a result*/
    public void startResultActivity(View view) {
        Intent intentResultActivity = new Intent(this, ResultActivity.class);
        startActivityForResult(intentResultActivity, REQUEST_CODE);
    }

    /*gets the returned result and displays the text*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            textView.setText("Name: " + data.getStringExtra("name"));
        }
    }
}
