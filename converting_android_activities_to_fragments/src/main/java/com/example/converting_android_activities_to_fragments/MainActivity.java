package com.example.converting_android_activities_to_fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/*
www.101apps.co.za
displays a button in a fragment
press button to swop fragment to list fragments that displays list of names*/
public class MainActivity extends AppCompatActivity
        implements ContactsListFragment.OnContactSelectedListener {

    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment_container_layout);

        fragmentManager = getFragmentManager();

        if (savedInstanceState == null) {
//            if it's the first time, create and display the button fragment
            ButtonFragment buttonFragment = new ButtonFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, buttonFragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            //activity recreated from saved state
        }
    }

    //called when button pressed, displays list of names in a new fragment
    public static void showContactsList() {
        ContactsListFragment contactListFragment = new ContactsListFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, contactListFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onContactSelected(int position) {
        DisplayContactFragment displayContactFragment = new DisplayContactFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        displayContactFragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, displayContactFragment)
                .addToBackStack(null)
                .commit();
    }
}
