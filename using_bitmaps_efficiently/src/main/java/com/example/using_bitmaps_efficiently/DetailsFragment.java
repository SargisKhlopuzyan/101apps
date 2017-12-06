package com.example.using_bitmaps_efficiently;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageViewIcon);
        TextView txtPhrase = (TextView) rootView.findViewById(R.id.textViewPhrase);
        TextView txtPhone = (TextView) rootView.findViewById(R.id.textViewPhone);

        // get fragment construction argumants - sent by fragment activity
        int personPhone = getArguments().getInt("personPhone");
        int personDrawableId = getArguments().getInt("personDrawableId");
        String personPhrase = getArguments().getString("personPhrase");

        txtPhrase.setText(personPhrase);
        txtPhone.setText(String.valueOf(personPhone));

        Picasso.with(getActivity())
                .load(personDrawableId)
                .resize(600, 600)
                .centerCrop()
                .into(imageView);

        return rootView;
    }
}
