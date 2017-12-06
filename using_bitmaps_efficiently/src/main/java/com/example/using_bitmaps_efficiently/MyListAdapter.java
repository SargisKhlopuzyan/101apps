package com.example.using_bitmaps_efficiently;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sargiskh on 12/6/2017.
 */

public class MyListAdapter extends ArrayAdapter<Person> {

    private Activity activity;
    private List<Person> people;

    public MyListAdapter(Activity activity, List<Person> people) {
        super(activity, R.layout.item_view, people);
        this.activity = activity;
        this.people = people;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // do we have a view
        View itemView = convertView;
        if (itemView == null) {
            itemView = activity.getLayoutInflater().inflate(R.layout.item_view, parent, false);
        }
        // get current person
        Person currentPerson = people.get(position);

        // fill the view
        ImageView imageView = (ImageView) itemView.findViewById(R.id.item_icon);
        // for development - shows the triangle - yellow=disk, green=memory

        // you need to include the Picasso Library - see tutorial
        Picasso.with(activity).setDebugging(true);
        Picasso.with(activity)
                .load(currentPerson.getIconId())
                .resize(100, 100).centerCrop()
                .into(imageView);

        TextView nameText = (TextView) itemView.findViewById(R.id.item_txtName);
        nameText.setText(currentPerson.getName());

        return itemView;
    }
}