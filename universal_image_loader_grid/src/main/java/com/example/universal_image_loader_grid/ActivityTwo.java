package com.example.universal_image_loader_grid;

/**
 * Created by clive on 11-Jun-14.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by clive on 03-Jun-14.
 * www.101apps.co.za
 */
public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        int position = getIntent().getIntExtra("position", -1);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setMaxHeight(600);
        imageView.setMaxWidth(600);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage("drawable://" + MainActivity.mThumbIds[position], imageView);

    }
}