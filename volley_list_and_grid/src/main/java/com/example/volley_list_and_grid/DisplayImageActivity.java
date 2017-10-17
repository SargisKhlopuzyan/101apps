package com.example.volley_list_and_grid;

import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by clive on 16-Jun-14.
 * www.101apps.co.za
 * displays the selected list/grid item image
 */

public class DisplayImageActivity extends MyMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        int imageIndex = getIntent().getIntExtra("position", -1);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        ImageLoader mImageLoader = MyVolleySingleton.getInstance(this).getImageLoader();
        mImageLoader.get(ImageUrlArray.IMAGES[imageIndex],
                ImageLoader.getImageListener(imageView,
                        R.drawable.no_image,
                        R.drawable.error),
                600,
                600
        );
    }
}
