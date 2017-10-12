package com.example.passing_serializable_objects;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Random;

/*created using Android Studio (Beta) 0.8.14
www.101apps.co.za*/

public class MainActivity extends AppCompatActivity {

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

    /*create a new country, using random integer as index to get
    the data out of the CountryData arrays*/
    private Country createCountry() {
        Random random = new Random();
        int index = random.nextInt(5);
        Country country;
        country = new Country(CountryData.country[index],
                CountryData.capital[index],
                CountryData.population[index]);
        return country;
    }

    /*pass the country object in an intent to the second activity*/
    public void onPassCountryClick(View view) {
        Intent intentPassCountry = new Intent(this, SecondActivity.class);
        intentPassCountry.putExtra("country", thisCountry);
        startActivity(intentPassCountry);
    }

}