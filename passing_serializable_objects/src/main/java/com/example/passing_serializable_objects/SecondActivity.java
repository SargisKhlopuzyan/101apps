package com.example.passing_serializable_objects;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/*created using Android Studio (Beta) 0.8.14
www.101apps.co.za*/

public class SecondActivity extends AppCompatActivity {

    private TextView textViewCountry;
    private TextView textViewCapital;
    private TextView textViewPopulation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        Intent intentThatStartedThisActivity = getIntent();

        Country serialisedCountryPassedInIntent
                = (Country) intentThatStartedThisActivity
                .getSerializableExtra("country");

        initialiseTextViews();
        displayThisCountryDetails(serialisedCountryPassedInIntent);
    }

    private void initialiseTextViews() {
        textViewCountry = (TextView) findViewById(R.id.textViewCountry);
        textViewCapital = (TextView) findViewById(R.id.textViewCapital);
        textViewPopulation = (TextView) findViewById(R.id.textViewPopulation);
    }

    private void displayThisCountryDetails(Country country) {
        textViewCountry.setText(country.getName());
        textViewCapital.setText(country.getCapital());
        textViewPopulation.setText(String.valueOf
                (country.getPopulation()) + " Million");
    }

}