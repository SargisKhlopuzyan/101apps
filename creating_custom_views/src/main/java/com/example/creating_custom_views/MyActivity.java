package com.example.creating_custom_views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/*displays a circle and a button. pressing
button changes circle color and displays new text label

created using Android Studio (Beta) 0.8.2
www.101apps.co.za
*/
public class MyActivity extends AppCompatActivity {
    private final float LARGE_TEXT_SIZE = 60;
    private final float MEDIUM_TEXT_SIZE = 40;
    private final float SMALL_TEXT_SIZE = 25;
    public int counter;

    private MyCustomView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        counter = 1;

        myView = (MyCustomView) findViewById(R.id.customView);
        myView.setCircleColor(Color.BLUE);
        myView.setCircleTextSize(MEDIUM_TEXT_SIZE);
        myView.setCircleTextColor(Color.RED);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (counter) {
                    case 1:
                        myView.setCircleColor(Color.RED);
                        myView.setCircleTextColor(Color.BLACK);
                        myView.setCircleTextSize(LARGE_TEXT_SIZE);
                        myView.setCircleText(getResources().getString(R.string.stop));
                        break;
                    case 2:
                        myView.setCircleColor(Color.GREEN);
                        myView.setCircleTextColor(Color.RED);
                        myView.setCircleTextSize(SMALL_TEXT_SIZE);
                        myView.setCircleText(getResources().getString(R.string.go));
                        break;
                    case 3:
                        myView.setCircleColor(Color.YELLOW);
                        myView.setCircleTextColor(Color.MAGENTA);
                        myView.setCircleTextSize(MEDIUM_TEXT_SIZE);
                        myView.setCircleText(getResources().getString(R.string.careful));
                        break;
                }

                counter++;
                if (counter > 3) counter = 1;
            }
        });
    }

}
