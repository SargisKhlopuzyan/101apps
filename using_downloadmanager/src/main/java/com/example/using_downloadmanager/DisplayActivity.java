package com.example.using_downloadmanager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by clive on 3/25/2014.
 *
 *      www.101apps.co.za
 */
public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        String uri = getIntent().getStringExtra("uri");

        Bitmap bitmap = BitmapFactory.decodeFile(uri);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
    }
}
