package com.example.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnImageItemSelectedListener {

    private FrameLayout container_items_fragment;
    private FrameLayout container_image_fragment;

//    private ItemFragment itemFragment;
//    private ImageFragment imageFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        container_items_fragment = findViewById(R.id.container_items_fragment);
        container_image_fragment = findViewById(R.id.container_image_fragment);

        Fragment itemFragment = getSupportFragmentManager().findFragmentByTag("ItemFragment");
        Fragment imageFragment = getSupportFragmentManager().findFragmentByTag("ImageFragment");

        if (itemFragment != null) {
            Log.e("LOG_TAG_X", "** 1 itemFragment != nul");
        }
        if (imageFragment != null) {
            Log.e("LOG_TAG_X", "** 1 imageFragment != null");
        }

        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        if (container_items_fragment != null) {
            if (itemFragment == null) {
                itemFragment = new ItemFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container_items_fragment, itemFragment, "ItemFragment").commit();
        }

        if (container_image_fragment != null) {
            if (imageFragment == null) {
                imageFragment = new ImageFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container_image_fragment, imageFragment, "ImageFragment").commit();
        }
    }

    public void onImageItemSelected(int position) {
        if (container_image_fragment != null) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_image_fragment);
            if (fragment == null) {
                Log.e("LOG_TAG", "*if*");
                fragment = new ImageFragment();
                Bundle args = new Bundle();
                args.putInt("position", position);
                fragment.setArguments(args);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container_image_fragment, fragment);
                transaction.commit();
            } else {
                Log.e("LOG_TAG", "*else*");
                ((ImageFragment)fragment).updateImage(position);
            }
        } else {
            ImageFragment imageFragment = new ImageFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            imageFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container_items_fragment, imageFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {

        Fragment itemFragment = getSupportFragmentManager().findFragmentByTag("ItemFragment");
        Fragment imageFragment = getSupportFragmentManager().findFragmentByTag("ImageFragment");

        if (itemFragment != null) {
            Log.e("LOG_TAG_X", "** 2 itemFragment != null");
        }
        if (imageFragment != null) {
            Log.e("LOG_TAG_X", "** 2 imageFragment != null");
        }

        super.onBackPressed();
    }
}