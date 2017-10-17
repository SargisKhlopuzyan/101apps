package com.example.volley_list_and_grid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by clive on 16-Jun-14.
 * www.101apps.co.za
 */
public class MyMenuActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.listviewItem:
                Intent intentList = new Intent(MyMenuActivity.this, MyListActivity.class);
                startActivity(intentList);
                return true;
            case R.id.gridviewItem:
                Intent intentGrid = new Intent(MyMenuActivity.this, GridViewActivity.class);
                startActivity(intentGrid);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
