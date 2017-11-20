package com.example.using_androids_file_system;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveFilePublicDirectory("sargis_demo_image");

    }

    private void saveFilePublicDirectory(String fileName) {
        Bitmap myImage = BitmapFactory.decodeResource(getResources(), R.drawable.demo_image);

        String diskState = Environment.getExternalStorageState();
        if (diskState.equals(Environment.MEDIA_MOUNTED)) {
            File pictureFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File filePicture = new File(pictureFolder, fileName);

            FileOutputStream out = null;

            try {
                out = new FileOutputStream(filePicture);
                myImage.compress(Bitmap.CompressFormat.JPEG, 80, out);
                out.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
