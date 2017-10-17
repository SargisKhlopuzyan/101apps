package com.example.volley_list_and_grid;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by clive on 16-Jun-14.
 * www.101apps.co.za
 */
public class MyListActivity extends ListActivity
        implements AdapterView.OnItemClickListener {

    ImageLoader mImageLoader;
    NetworkImageView mNetworkImageView;
    MyArrayAdapter imageArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mImageLoader = MyVolleySingleton.getInstance(this).getImageLoader();

        imageArrayAdapter = new MyArrayAdapter(this,android.R.layout.simple_list_item_1);
        setListAdapter(imageArrayAdapter);

        ListView listView = getListView();
        listView.setOnItemClickListener(this);
    }

//    display the selected image in new activity
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MyListActivity.this, DisplayImageActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    class MyArrayAdapter extends ArrayAdapter<String> {

        public MyArrayAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public int getCount() {
            return ImageUrlArray.IMAGES.length;
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.list_image_item, parent, false);
            }

            mNetworkImageView = (NetworkImageView) itemView.findViewById(R.id.networkImageView);
            mNetworkImageView.setDefaultImageResId(R.drawable.no_image);
            mNetworkImageView.setErrorImageResId(R.drawable.error);
            mNetworkImageView.setAdjustViewBounds(true);
            mNetworkImageView.setImageUrl(ImageUrlArray.IMAGES[position], mImageLoader);
            return itemView;
        }
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
                Intent intentList = new Intent(MyListActivity.this, MyListActivity.class);
                startActivity(intentList);
                return true;
            case R.id.gridviewItem:
                Intent intentGrid = new Intent(MyListActivity.this, GridViewActivity.class);
                startActivity(intentGrid);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
