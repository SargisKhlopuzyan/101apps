package com.example.android_recyclerview_and_picasso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created using Android Studio (Beta) 0.8.14
 * www.101apps.co.za
 */
public class WebActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webview = new WebView(this);

        setContentView(R.layout.progressbar);

        String url = getIntent().getStringExtra("articleUrl");
        if (url == null) {
            Log.e("LOG_TAG", "url == null");
            url = "http://www.101apps.co.za/";
        }
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                setContentView(webview);
            }
        });
    }
}
