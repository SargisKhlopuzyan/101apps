package com.example.intents_and_intent_filters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by clive on 28-Jun-14.
 * started by button press in main activity
 * returns a string result
 * www.101apps.co.za
 */
public class ResultActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intentReturnResult = new Intent();
        intentReturnResult.putExtra("name", "Charlie Chaplin");
        setResult(RESULT_OK,intentReturnResult);
        finish();
    }
}
