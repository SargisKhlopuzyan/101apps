package com.example.using_an_asynctask;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private String TAG = "LOG_TAG";
//    private final String IMAGE_URL = "http://www.101apps.co.za/images/headers/101_logo_very_small.jpg";
//    private final String IMAGE_NAME = "101_logo_very_small.jpg";
    private final String IMAGE_URL = "http://www.androidbegin.com/wp-content/uploads/2013/07/HD-Logo.gif";
    private final String IMAGE_NAME = "HD-Logo.gif";
    public  String IMAGE_PHONE_LOCATION_PATH = Environment.getExternalStorageDirectory() + File.separator + IMAGE_NAME;

    private ImageView myImageView;
    private ProgressBar progressbar;
    private int PROGRESS_BAR_MAX = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        myImageView = (ImageView) findViewById(R.id.imageView);
        Button buttonAsyncTask = (Button) findViewById(R.id.button);
        progressbar = (ProgressBar) findViewById(R.id.progressBar);
        progressbar.setMax(PROGRESS_BAR_MAX);

        buttonAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyAsyncTask().execute(IMAGE_URL);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED && grantResults[1]== PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            Log.v(TAG,"Permission: "+permissions[1]+ "was "+grantResults[1]);
            //resume tasks needing this permission
        }
    }

    public class MyAsyncTask extends AsyncTask<String, Integer, String> {

        public MyAsyncTask() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(IMAGE_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoOutput(true);
                connection.connect();

                File imageFile = new File(Environment.getExternalStorageDirectory(), IMAGE_NAME);
                FileOutputStream outputStream = new FileOutputStream(imageFile);
                InputStream inputStream = connection.getInputStream();

                int length = connection.getContentLength();
                Log.e(TAG, "length: " + length);

                byte[] buffer = new byte[1024];
                int read = 0;
                int downloaded = 0;
                while ((read = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, read);
                    downloaded += read;
                    int progress = (downloaded*100)/length;
                    Log.e(TAG, "progress: " + progress);
                    publishProgress(progress);
                    SystemClock.sleep(1000);
                }
                outputStream.close();
            } catch (FileNotFoundException e) {
                Log.e(TAG, "FileNotFoundException");
            } catch (ProtocolException e) {
                Log.e(TAG, "ProtocolException");
            } catch (MalformedURLException e) {
                Log.e(TAG, "MalformedURLException");
            } catch (IOException e) {
                Log.e(TAG, "IOException");
            }

            return IMAGE_PHONE_LOCATION_PATH;

            /*
            String result = "";
            String path = Environment.getExternalStorageDirectory() + File.pathSeparator + IMAGE_NAME;

            HttpURLConnection connection = null;
            try {
                final URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setDoOutput(true);
                connection.connect();

                //for progress bar
                int length = connection.getContentLength();
                InputStream is = (InputStream) url.getContent();
                byte[] imageData = new byte[length];
                int bufferSize = (int) Math.ceil(length /
                        (double) PROGRESS_BAR_MAX);
                int downloaded = 0;
                for (int i = 1; i < PROGRESS_BAR_MAX; i++) {
                    int read = is.read(imageData, downloaded, bufferSize);
                    downloaded += read;
                    //set bar to current download progress
                    publishProgress(i);
//                    Log.e(TAG, "i: " + i);
                }
                //

                BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                FileOutputStream out = new FileOutputStream(path);
                int data = in.read();
                int k = 0;
                while (data != -1) {
                    Log.e(TAG, "k: " + (++k));
                    out.write(data);
                    data = in.read();
                }
            } catch (IOException e) {
                Log.e(TAG, "error loading image: " + e);
                e.printStackTrace();
                return null;
            } finally {
                if (connection == null) {
                    connection.disconnect();
                }
                // download finished - set bar to max
                publishProgress(PROGRESS_BAR_MAX);

                return path;
            }
            */
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            progressbar.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String imagePath) {
            super.onPostExecute(imagePath);
            if (imagePath != null) {
                progressbar.setVisibility(View.INVISIBLE);
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                if (bitmap == null) {
                    Log.e(TAG, "bitmap == null");
                }
                myImageView.setImageBitmap(bitmap);
            }
        }
    }
}