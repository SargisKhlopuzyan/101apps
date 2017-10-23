package com.example.threads;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutorials.R;


/*demonstrates the use of threads. One started in onCreate and
does work in background without modifying the UI. The other
thread is started with a button press. It modifies the UI
 by displaying a downloaded image

       www.101apps.co.za

 */
public class MainActivity extends AppCompatActivity implements Runnable {

    private int counter;
    private TextView textViewCounter;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCounter = (TextView) findViewById(R.id.textViewCounter);
        imageView = (ImageView) findViewById(R.id.imageView);

        counter = 0;

//        starts a thread to do background work without updating the UI
        Thread simpleThread = new Thread(MainActivity.this);
//        this ensures that this thread will be killed when the main thread is killed
        simpleThread.setDaemon(true);
        simpleThread.start();

        /*press to download the image and display it*/
        Button buttonThread = (Button) findViewById(R.id.button);
        buttonThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Thread myThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        download the image
                        final Drawable downloadedImage = downloadImage();

//                        update the UI thread to display the image
                        imageView.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Image downloaded", Toast.LENGTH_SHORT).show();
                                imageView.setImageDrawable(downloadedImage);
                            }
                        });
                    }
                });
                myThread.start();

            }
        });

        /*press to go to next activity*/
        Button buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                Intent intent = new Intent(MainActivity.this, HandlerActivity.class);
                startActivity(intent);
            }
        });

        /*each press advances the counter and displays it*/
        Button buttonCounter = (Button) findViewById(R.id.buttonCounter);
        buttonCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                textViewCounter.setText(String.valueOf(counter));
            }
        });
    }


    /*dummy image download gets the image from the drawables folder*/
    private Drawable downloadImage() {
        goToSleep();
        Drawable drawableImage = getResources().getDrawable(R.drawable.logo);
        return drawableImage;
    }

    //    run as per thread in onCreate
    @Override
    public void run() {
//        puts the thread in the background
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
        goToSleep();
        counter = 1000;
    }

//    sleeps for milliseconds
    private void goToSleep() {
        SystemClock.sleep(2000);
    }

}
