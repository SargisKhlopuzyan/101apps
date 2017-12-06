package com.example.using_bitmaps_efficiently;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Person> people = new ArrayList<Person>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populatePersonList();
        populateListView();
        selectedItemCallback();
    }

    private void selectedItemCallback() {

        ListView list = (ListView) findViewById(R.id.personListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Person clickedPerson = people.get(position);
                // get details for selected person
                int personPhone = clickedPerson.getPhone();
                int personDrawableId = clickedPerson.getIconId();
                String personPhrase = clickedPerson.getPhrase();

                // create intent and add details to it to send to create fragment
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
        // you need to add your own images in the drawable folder
        people.add(new Person("Mr Cool", 123456, R.raw.cool, "No job too cool!"));
        people.add(new Person("Cry Baby", 981945, R.raw.crying, "I cry for you"));
        people.add(new Person("Forever lost", 654876, R.raw.question, "Finding myself"));
        people.add(new Person("Sad Sadie", 982629, R.raw.sad, "Tears of joy?"));
        people.add(new Person("Golden Silence", 871962, R.raw.silence, "So deep"));
        people.add(new Person("Sleeping Beauty", 108529, R.raw.sleeping, "Wake me up"));
        people.add(new Person("Happy Harry", 982052, R.raw.smiling, "Dull, hell no!"));
        people.add(new Person("Don't worry", 982052, R.raw.winking, "Our secret!"));
        people.add(new Person("Worried Willy", 982052, R.raw.worried, "Got money?"));
    }

    private void populateListView() {
        ArrayAdapter<Person> adapter = new MyListAdapter(this, people);
        ListView list = (ListView) findViewById(R.id.personListView);
        list.setAdapter(adapter);
    }
}
