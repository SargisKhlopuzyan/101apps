package com.example.android_styles_and_themes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/*created using Android Studio (Beta) 0.8.2
www.101apps.co.za*/
public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    public void goThere(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }
}
