package com.example.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by clive on 04-Apr-14.
 * Creates the item list fragment
 */
public class ItemFragment extends ListFragment {

    OnImageItemSelectedListener mCallback;

    String[] imageItemsArray = {"Apple", "Cherries", "Lemon", "Orange", "Pear", "Strawberry"};

    public interface OnImageItemSelectedListener {
        public void onImageItemSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("LOG_TAG", "1: onCreate");
        // we need to use a different list item layout for devices older than Honeycomb
        int layout;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // needs API Level 11
            layout = android.R.layout.simple_list_item_activated_1;
        } else {
            // used as of API Level 1
            layout = android.R.layout.simple_list_item_1;
        }
        // Create an array adapter for the list view, using the imageItemsArray array
        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, imageItemsArray));
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("LOG_TAG", "1: onStart");
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        Log.e("LOG_TAG", "1: onAttach");

        // makes sure parent MainActivity implements
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnImageItemSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " The MainActivity activity must implement OnImageItemSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.e("LOG_TAG", "1: onListItemClick - position: " + position);
        // Notify the MainActivity of selected item
        mCallback.onImageItemSelected(position);
    }
}
