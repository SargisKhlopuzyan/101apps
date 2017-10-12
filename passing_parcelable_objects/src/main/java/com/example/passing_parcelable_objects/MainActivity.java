package com.example.passing_parcelable_objects;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * Created using Android Studio (Beta) 0.8.14
 * www.101apps.co.za
 */

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";
    private Country thisCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        thisCountry = createCountry();
    }

    private Country createCountry() {
        Log.i(TAG, "creating a country object");
        Random random = new Random();
        int index = random.nextInt(5);
        Country country = new Country();
        country.setName(CountryData.country[index]);
        country.setCapital(CountryData.capital[index]);
        country.setPopulation(CountryData.population[index]);
        return country;
    }

    public void onPassCountryClick(View view) {
        Log.i(TAG, "in onPassCountryClick() - about to pass the Country object");
        Intent intentPassCountry = new Intent(this, SecondActivity.class);
        ParcelableCountry parcelableCountry = new ParcelableCountry(thisCountry);
        Log.i(TAG, "in onPassCountryClick() - putting parcelable object in intent");
        intentPassCountry.putExtra("country", parcelableCountry);
        Log.i(TAG, "in onPassCountryClick() - starting SecondActivity, passing the intent");
        startActivity(intentPassCountry);
    }
}
