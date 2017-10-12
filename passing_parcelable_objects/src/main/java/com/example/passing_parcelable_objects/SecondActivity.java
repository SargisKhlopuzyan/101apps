package com.example.passing_parcelable_objects;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

/*created using Android Studio (Beta) 0.8.14
www.101apps.co.za*/

public class SecondActivity extends ActionBarActivity{

    private static final String TAG = "SecondActivity";
    private TextView textViewCountry;
    private TextView textViewCapital;
    private TextView textViewPopulation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        Intent intentThatStartedThisActivity = getIntent();
        Log.i(TAG, "received parcel from intent");
        ParcelableCountry parcelableCountry = intentThatStartedThisActivity.getParcelableExtra("country");
        Log.i(TAG, "getting the country out of the parcel");
        Country country = parcelableCountry.getCountry();
        initialiseTextViews();
        displayThisCountryDetails(country);
    }

    private void initialiseTextViews() {
        Log.i(TAG, "initialising the text views");
        textViewCountry = (TextView) findViewById(R.id.textViewCountry);
        textViewCapital = (TextView) findViewById(R.id.textViewCapital);
        textViewPopulation = (TextView) findViewById(R.id.textViewPopulation);
    }

    private void displayThisCountryDetails(Country country) {
        Log.i(TAG, "showing the Country fields");
        textViewCountry.setText(country.getName());
        textViewCapital.setText(country.getCapital());
        textViewPopulation.setText(String.valueOf
                (country.getPopulation()) + " Million");
    }
}