package com.example.fragments_configuration_changes_and_retaining_objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

/**
 * Created by clive on 20-May-14.
 * displays bitmap and some text fields.
 * saves bitmap and text data in a fragment which is
 * saved on configuration change. saved fragment is used to rebuild
 * the activity after configuration change
 * www.101apps.co.za
 */
public class MyActivity extends FragmentActivity {

    private SavedFragment dataFragment;
    private MyDataObject newObject;
    private String theName;
    private String theEmail;
    private String thePhone;
    private Bitmap theBitmap;
    private static boolean isDataLoaded;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myactivity);

        newObject = new MyDataObject();

        setDefaultData();

//        set up text views and button
        final TextView nameTextView = (TextView) findViewById(R.id.textViewName);
        final TextView emailTextView = (TextView) findViewById(R.id.textViewEmail);
        final TextView phoneTextView = (TextView) findViewById(R.id.textViewPhone);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            get your data
                String[] myStringDataArray = downloadMyData();
//               put the data into the data object
                newObject.setName(myStringDataArray[0]);
                newObject.setEmail(myStringDataArray[1]);
                newObject.setPhone(myStringDataArray[2]);
                newObject.setBitmap(downloadMyBitmap());
//        get the data out of the data object and display
                nameTextView.setText(newObject.getName());
                emailTextView.setText(newObject.getEmail());
                phoneTextView.setText(newObject.getPhone());
                imageView.setImageBitmap(newObject.getBitmap());
//                set the data in the fragment
                dataFragment.setData(newObject);
                isDataLoaded = true;
            }
        });

        // get the saved fragment on activity restarts
        FragmentManager fm = getSupportFragmentManager();
        dataFragment = (SavedFragment) fm.findFragmentByTag("data");

//        we're running the activity for the first time
        if (savedInstanceState == null) {
            // create the fragment and data for the first time
            dataFragment = new SavedFragment();
            // add the fragment
            fm.beginTransaction().add(dataFragment, "data").commit();
        } else {
            if (isDataLoaded) {
          /* we're re-creating the activity and should restore it's state
            using the data saved in the fragment*/
//                get the data object out of the fragment
                newObject = dataFragment.getData();
//                get the data out of the object
                theName = newObject.getName();
                theEmail = newObject.getEmail();
                thePhone = newObject.getPhone();
                theBitmap = newObject.getBitmap();
            } else {
//                we never downloaded anything so use the default data
                setDefaultData();
            }
        }

//        display the data
        nameTextView.setText(theName);
        emailTextView.setText(theEmail);
        phoneTextView.setText(thePhone);
        imageView.setImageBitmap(theBitmap);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    // our default data to display
    private void setDefaultData() {
        theName = "Harry";
        theEmail = "harry@sally.com";
        thePhone = "12345";
        theBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher);
    }

    // let's pretend that we're getting our data from an internet download
    private String[] downloadMyData() {
        theName = "Jack";
        theEmail = "jack@sprat.com";
        thePhone = "09876543";
        String[] tempStringArray = {theName, theEmail, thePhone};
        return tempStringArray;
    }

    // let's pretend that we're getting our bitmap from an internet download
    private Bitmap downloadMyBitmap() {
        InputStream inputStream = getResources().openRawResource(R.raw.balls);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       /* we're about to destroy the activity
        so make sure we store the latest data in the fragment*/
        dataFragment.setData(newObject);
    }
}
