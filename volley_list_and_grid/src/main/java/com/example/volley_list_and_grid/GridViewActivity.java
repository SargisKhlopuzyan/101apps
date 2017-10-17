package com.example.volley_list_and_grid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by clive on 16-Jun-14.
 * www.101apps.co.za
 */
public class GridViewActivity extends MyMenuActivity {

    ImageLoader mImageLoader;
    NetworkImageView mNetworkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        mImageLoader = MyVolleySingleton.getInstance(this).getImageLoader();

        final GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new MyImageAdapter());

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                display selected image in new activity
                Intent intent = new Intent(GridViewActivity.this, DisplayImageActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    static class ViewHolder {
        ImageView imageView;
    }

    class MyImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return ImageUrlArray.IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;
            final ViewHolder gridViewImageHolder;
//            check to see if we have a view
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.grid_image_item, parent, false);
                gridViewImageHolder = new ViewHolder();
                gridViewImageHolder.imageView = (ImageView) view.findViewById(R.id.networkImageView);
                view.setTag(gridViewImageHolder);
            } else {
                gridViewImageHolder = (ViewHolder) view.getTag();
            }

            mNetworkImageView = (NetworkImageView) gridViewImageHolder.imageView;
            mNetworkImageView.setDefaultImageResId(R.drawable.no_image);
            mNetworkImageView.setErrorImageResId(R.drawable.error);
            mNetworkImageView.setAdjustViewBounds(true);
            mNetworkImageView.setImageUrl(ImageUrlArray.IMAGES[position], mImageLoader);

            return view;
        }
    }
}