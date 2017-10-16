package com.example.universal_image_loader_grid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

//www.101apps.co.za
public class MainActivity extends AppCompatActivity {

    DisplayImageOptions options;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageLoader = ImageLoader.getInstance();

//        set options for image display
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.place_holder)
                .showImageForEmptyUri(R.drawable.hand)
                .showImageOnFail(R.drawable.big_problem)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter());
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                gridview.getAdapter().getItem(position);
                Intent intent = new Intent(MainActivity.this, ActivityTwo.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    static class ViewHolder {
        ImageView imageView;
    }

    //    our custom adapter
    private class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mThumbIds.length;
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
            if (convertView == null) {
//                no view - so create a new one
                view = getLayoutInflater().inflate(R.layout.item_grid_image, parent, false);
                gridViewImageHolder = new ViewHolder();
                gridViewImageHolder.imageView = (ImageView) view.findViewById(R.id.image);
                gridViewImageHolder.imageView.setMaxHeight(80);
                gridViewImageHolder.imageView.setMaxWidth(80);
                view.setTag(gridViewImageHolder);
            } else {
//                we've got a view
                gridViewImageHolder = (ViewHolder) view.getTag();
            }
            imageLoader.displayImage("drawable://" + mThumbIds[position], gridViewImageHolder.imageView, options);

            return view;
        }
    }

    static Integer[] mThumbIds = {R.drawable.amazed, R.drawable.angelic,
            R.drawable.cool, R.drawable.crying, R.drawable.devil,
            R.drawable.laughing, R.drawable.loving, R.drawable.question,
            R.drawable.sad, R.drawable.silence, R.drawable.simple, R.drawable.sleeping,
            R.drawable.smiling, R.drawable.tongue, R.drawable.winking, R.drawable.worried,
            R.drawable.amazed, R.drawable.angelic, R.drawable.cool, R.drawable.crying,
            R.drawable.devil, R.drawable.laughing, R.drawable.loving, R.drawable.question,
            R.drawable.sad, R.drawable.silence, R.drawable.simple, R.drawable.sleeping,
            R.drawable.smiling, R.drawable.tongue, R.drawable.winking, R.drawable.worried};
}

