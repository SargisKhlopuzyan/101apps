package com.example.universal_image_loader_list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * Created by clive on 01-Jun-14.
 * www.101apps.co.za
 */
public class DetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        get fragment construction arguments - sent by fragment activity
        int personPhone = getArguments().getInt("personPhone");
        int personDrawableId = getArguments().getInt("personDrawableId");
        String personPhrase = getArguments().getString("personPhrase");

        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageViewIcon);
        imageView.setMaxHeight(400);
        imageView.setMaxWidth(400);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage("drawable://" + personDrawableId, imageView);

        TextView txtPhrase = (TextView) rootView.findViewById(R.id.textViewPhrase);
        txtPhrase.setText(personPhrase);

        TextView txtPhone = (TextView) rootView.findViewById(R.id.textViewPhone);
        txtPhone.setText(String.valueOf(personPhone));

        return rootView;
    }
}
