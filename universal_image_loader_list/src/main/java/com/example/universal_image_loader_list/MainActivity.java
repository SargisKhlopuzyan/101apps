package com.example.universal_image_loader_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

//www.101apps.co.za

public class MainActivity extends AppCompatActivity {

    private List<Person> people;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.place_holder)
                .showImageForEmptyUri(R.drawable.question)
                .showImageOnFail(R.drawable.big_problem)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        imageLoader = ImageLoader.getInstance();

        populatePersonList();
        populateListView();
        selectedItemCallback();
    }

    private static class ViewHolder {
        TextView textViewName;
        ImageView imageViewIcon;
    }

    private void selectedItemCallback() {
        ListView list = (ListView) findViewById(R.id.personListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person clickedPerson = people.get(position);
//                get details for selected person
                int personPhone = clickedPerson.getPhone();
                int personDrawableId = clickedPerson.getIconId();
                String personPhrase = clickedPerson.getPhrase();

//                create intent and add details to it to send to create fragment
                Intent intent = new Intent(MainActivity.this, DetailsFragmentActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("personPhone", personPhone);
                intent.putExtra("personDrawableId", personDrawableId);
                intent.putExtra("personPhrase", personPhrase);
                startActivity(intent);
            }
        });
    }

    private void populatePersonList() {
        people = new ArrayList<Person>();
        people.add(new Person("Mr Cool", 123456, R.drawable.cool, "No job too cool!"));
        people.add(new Person("Cry Baby", 981945, R.drawable.crying, "I cry for you"));
        people.add(new Person("Journey on", 654876, R.drawable.hand, "Bye-bye!"));
        people.add(new Person("Sad Sadie", 982629, R.drawable.sad, "Tears of joy?"));
        people.add(new Person("Golden Silence", 871962, R.drawable.silence, "So deep"));
        people.add(new Person("Sleeping Beauty", 108529, R.drawable.sleeping, "Wake me up"));
        people.add(new Person("Happy Harry", 982052, R.drawable.smiling, "Dull, hell no!"));
        people.add(new Person("Don't worry", 982052, R.drawable.winking, "Our secret!"));
        people.add(new Person("Worried Willy", 982052, R.drawable.worried, "Got money?"));
    }

    private void populateListView() {
        ArrayAdapter<Person> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.personListView);
        list.setAdapter(adapter);
    }

    //    our custom list adapter
    private class MyListAdapter extends ArrayAdapter<Person> {

        private MyListAdapter() {
            super(MainActivity.this, R.layout.item_view, people);
        }

        @Override
        public int getCount() {
            return people.size();
        }

        @Override
        public Person getItem(int position) {
            Person thisPerson = people.get(position);
            return thisPerson;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            final ViewHolder currentPersonViewHolder;
            //            do we have a view
            if (convertView == null) {
//                we don't have a view so create one by inflating the layout
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);

                currentPersonViewHolder = new ViewHolder();
                currentPersonViewHolder.textViewName = (TextView) itemView.findViewById(R.id.item_txtName);
                currentPersonViewHolder.imageViewIcon = (ImageView) itemView.findViewById(R.id.item_icon);
//                set the tag for this view to the current person view holder
                itemView.setTag(currentPersonViewHolder);

            } else {
//                we have a view so get the tagged view
                currentPersonViewHolder = (ViewHolder) itemView.getTag();
            }

//            display the current person's name
            currentPersonViewHolder.textViewName.setText(people.get(position).getName());
            /*    set max sizes of required image
            - used to compute decoding image so can save memory*/
            currentPersonViewHolder.imageViewIcon.setMaxWidth(100);
            currentPersonViewHolder.imageViewIcon.setMaxHeight(100);

//            display the current person's image
            imageLoader.displayImage("drawable://"
                    + people.get(position).getIconId()//the drawable
                    , currentPersonViewHolder.imageViewIcon//the image view
                    , options);//the display options

            return itemView;
        }
    }
}
