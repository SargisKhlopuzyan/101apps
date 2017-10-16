package com.example.universal_image_loader_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;//pre-honeycomb (Android 3.0)

/**
 * Created by clive on 01-Jun-14.
 * www.101apps.co.za
 */
public class DetailsFragmentActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

//get the selected items values out of intent sent by main activity on click event
        Intent intent = getIntent();
        int personPhone = intent.getIntExtra("personPhone", -1);
        int personDrawableId = intent.getIntExtra("personDrawableId", -1);
        String personPhrase = intent.getStringExtra("personPhrase");

//        create intent to send construction arguments for fragment
        DetailsFragment myFragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("personPhone", personPhone);
        args.putInt("personDrawableId", personDrawableId);
        args.putString("personPhrase", personPhrase);
        myFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, myFragment).commit();

    }
}
