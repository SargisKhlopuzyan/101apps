package com.example.creating_custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * creates a custom view consisting of a colored circle
 * Created by clive on 27-Jul-14.
 * created using Android Studio (Beta) 0.8.2
 * www.101apps.co.za
 */
public class MyCustomView extends View {

    private float circleTextSize;
    private int circleColour;
    private int circleTextColor;

    private String circleText;
    private Paint paintColorStyle;

    private static final float MEDIUM_TEXT_SIZE = 40;

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintColorStyle = new Paint();
        TypedArray attributeValuesArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyCustomView, 0, 0);
        try {
            circleText = attributeValuesArray.getString(R.styleable.MyCustomView_circleText);
            circleColour = attributeValuesArray.getInteger(R.styleable.MyCustomView_circleColor, 0);
            circleTextColor = attributeValuesArray.getInteger(R.styleable.MyCustomView_circleTextColor, 0);
            circleTextSize = attributeValuesArray.getFloat(R.styleable.MyCustomView_circleTextSize, MEDIUM_TEXT_SIZE);
        } finally {
            attributeValuesArray.recycle();
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        paintColorStyle.setStyle(Paint.Style.FILL);
        paintColorStyle.setAntiAlias(true);
        paintColorStyle.setColor(circleColour);

        int centerX = this.getMeasuredWidth() / 2;
        int centerY = this.getMeasuredHeight() / 2;
        int radius = 150;
        canvas.drawCircle(centerX, centerY, radius, paintColorStyle);

        paintColorStyle.setColor(circleTextColor);
        paintColorStyle.setTextAlign(Paint.Align.CENTER);

        canvas.drawText(circleText, centerX, centerY, paintColorStyle);
    }

    public int getCircleColour() {
        return circleColour;
    }

    public int getCircleTextColor() {
        return circleTextColor;
    }

    public String getCircleText() {
        return circleText;
    }

    public float getCircleTextSize() {
        circleTextSize = paintColorStyle.getTextSize();
        return circleTextSize;
    }

    public void setCircleColor(int newCircleColor) {
        circleColour = newCircleColor;
        invalidate();
        requestLayout();
    }

    public void setCircleTextColor(int newCircleTextColor) {
        circleTextColor = newCircleTextColor;
        invalidate();
        requestLayout();
    }

    public void setCircleText(String newCircleText) {
        circleText = newCircleText;
        invalidate();
        requestLayout();
    }

    public void setCircleTextSize(float newCircleTextSize) {
        paintColorStyle.setTextSize(newCircleTextSize);
        invalidate();
        requestLayout();
    }
}
