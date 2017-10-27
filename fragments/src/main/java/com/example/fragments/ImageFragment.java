package com.example.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by clive on 04-Apr-14.
 * Creates the image fragment
 */
public class ImageFragment extends Fragment {

    int[] drawableIdsArray = {R.drawable.apple, R.drawable.cherry, R.drawable.lemon, R.drawable.orange, R.drawable.pear, R.drawable.strawberry};
    private static int mCurrentPosition = -1;

    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("LOG_TAG", "2: onCreateView");
        View view = inflater.inflate(R.layout.image_layout, container, false);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("LOG_TAG", "2: onStart");
        //check if there are arguments passed to the fragment.
        Bundle args = getArguments();
        if (args != null) {
            // Set image based on argument passed in
            updateImage(args.getInt("position"));
        } else if (mCurrentPosition != -1) {
            // Set image based on saved instance state
            // defined during onCreateView
            updateImage(mCurrentPosition);
        }
    }

    public void updateImage(int position) {
        Log.e("LOG_TAG", "2: updateImage");
        mCurrentPosition = position;
        imageView.setImageResource(drawableIdsArray[position]);
    }
}
