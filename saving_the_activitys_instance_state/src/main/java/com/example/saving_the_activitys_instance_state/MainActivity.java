package com.example.saving_the_activitys_instance_state;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String SAVED_STATE_COUNTER_KEY = "saved_text";

    String name = "Lucky Lucy";
    String email = "lucky@lucy.com";
    String phone = "098-123-456";

    private EditText editName;
    private EditText editEmail;
    private EditText editPhone;
    private TextView textViewCounter;
    private TextView textViewPrefCounter;
    private Button button;
    private Button buttonAddText;

    int prefCountText;
    private String countText;

    private String TAG = "LOG_TAG";
    private SharedPreferences activityPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate: - setting up the activity");

        editName = (EditText) findViewById(R.id.editTextName);
        editEmail = (EditText) findViewById(R.id.editTextEmail);
        editPhone = (EditText) findViewById(R.id.editTextPhone);
        textViewCounter = (TextView) findViewById(R.id.textView);
        textViewPrefCounter = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button);
        buttonAddText = (Button) findViewById(R.id.button2);

        activityPreferences = getPreferences(Activity.MODE_PRIVATE);
        prefCountText = activityPreferences.getInt("counter", -1);
        if(prefCountText == -1) {
            prefCountText = 0;
        }
        countText = "0";

//        Log.i(TAG, "prefCountText: " + prefCountText);
//        Log.i(TAG, "countText: " + countText);

        textViewPrefCounter.setText(String.valueOf(prefCountText));
        textViewCounter.setText(countText);

//        if there is a saved bundle we need to restore the User Interface
        if (savedInstanceState != null && savedInstanceState.containsKey(SAVED_STATE_COUNTER_KEY)) {
            Log.i(TAG, "onCreate: - Restoring from saved bundle");
//            restore state here from bundle or in onRestoreInstanceState
            countText = savedInstanceState.getString(SAVED_STATE_COUNTER_KEY);
            textViewCounter.setText(countText);
        }

//        increases the value of the counter and displays this value in both text fields
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefCountText++;
                textViewCounter.setText(String.valueOf(prefCountText));
                textViewPrefCounter.setText(String.valueOf(prefCountText));
            }
        });

//        fills in user name, email, phone number and resets the textViews to zero also sets the shared pref value of prefCountText to zero
        buttonAddText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editName.setText(name);
                editEmail.setText(email);
                editPhone.setText(phone);

                prefCountText = 0;
                countText = "0";

//                reset the Shared Prefs file to zero
                activityPreferences = getPreferences(Activity.MODE_PRIVATE);
                editor = activityPreferences.edit();
                editor.putInt("counter", prefCountText).commit();

                textViewPrefCounter.setText(String.valueOf(prefCountText));
                textViewCounter.setText(countText);
            }
        });
    }

//    gets the last save value for prefCountText out of the Shared Preference file and displays it in the textview
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: getting persistent data");
        activityPreferences = getPreferences(Activity.MODE_PRIVATE);
        prefCountText = activityPreferences.getInt("counter", -1);
        textViewPrefCounter.setText(String.valueOf(prefCountText));
    }

//    saves the current value of prefCountText as a Shared Preference
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: saving persistent data");
        activityPreferences = getPreferences(Activity.MODE_PRIVATE);
        editor = activityPreferences.edit();
        editor.putInt("counter", prefCountText).commit();
    }

//    saves additional UI info
    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        Log.i(TAG, "onSaveInstanceState: - saving UI state");
        TextView textView = (TextView) findViewById(R.id.textView);
        String countText = textView.getText().toString();
        saveInstanceState.putString(SAVED_STATE_COUNTER_KEY,countText);
//       call the superclass last so that it saves the view hierarchy state
        super.onSaveInstanceState(saveInstanceState);
    }

//    restores the activity's instance state
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        only called if there is a saved state to restore
        Log.i(TAG, "onRestoreInstanceState: called - restoring UI state in onRestoreInstanceState");
//        always call the superclass first so it can restore the vew hierarchy
        super.onRestoreInstanceState(savedInstanceState);

//        restore state here or in onCreate
        countText = savedInstanceState.getString(SAVED_STATE_COUNTER_KEY);
        textViewCounter.setText(countText);
    }
}
