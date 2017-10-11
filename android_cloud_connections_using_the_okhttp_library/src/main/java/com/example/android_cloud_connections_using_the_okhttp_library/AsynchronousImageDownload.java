package  com.example.android_cloud_connections_using_the_okhttp_library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/*created using Android Studio (Beta) 0.8.14
www.101apps.co.za*/

public class AsynchronousImageDownload extends AppCompatActivity {

    private static final String TAG = "TAG_okhttp";
    String imageUrl = "http://www.101apps.co.za/images/headers/101_logo_very_small.jpg";
    private ImageView imageView;
    private OkHttpClient client;
    private boolean isImageDisplaying = false;
    private boolean isRequestProblem = false;
    private boolean isResponseProblem = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynchronous_image_download);

        if (savedInstanceState != null) {
            isImageDisplaying = savedInstanceState.getBoolean("isImageDisplaying");
            isRequestProblem = savedInstanceState.getBoolean("isRequestProblem");
            isResponseProblem = savedInstanceState.getBoolean("isResponseProblem");
        }

        File cacheDirectory = new File(this.getCacheDir(), "http");
        int cacheSize = 10 * 1024 * 1024; // 2M
        Cache cache = new Cache(cacheDirectory, cacheSize);
        OkHttpClient.Builder builder = new OkHttpClient.Builder().cache(cache);
        client = builder.build();

        imageView = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume called");
        if (isRequestProblem) {
            Log.i(TAG, "1: isRequestProblem");
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.failed));
        } else if (isResponseProblem) {
            Log.i(TAG, "2: isResponseProblem");
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.response_problem));
        } else if (isImageDisplaying) {
            Log.i(TAG, "3: isImageDisplaying");
            if (!isOnline()) {
                Log.i(TAG, "3.1: !isOnline()");
                Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
            asynchronousDownloadImage(imageUrl);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isImageDisplaying", isImageDisplaying);
        outState.putBoolean("isRequestProblem", isRequestProblem);
        outState.putBoolean("isResponseProblem", isResponseProblem);
    }

    public void onClickDownloadImage(View view) {
        if (!isOnline()) {
            Log.i(TAG, "*** onClickDownloadImage: !isOnline()");
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
        Log.i(TAG, "*** onClickDownloadImage");
        asynchronousDownloadImage(imageUrl);
    }

    private void asynchronousDownloadImage(String imageUrl) {

        Request request = new Request.Builder()
                .url(imageUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {

            public static final int SUCCESSFUL_DOWNLOAD = 200;

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                isRequestProblem = true;
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "onFailure");
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.failed));
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //getResponseDetails(response);

                if (!response.isSuccessful()) {
                    isResponseProblem = true;
                    imageView.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "onResponse : response_problem");
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.response_problem));
                        }
                    });
                    throw new IOException("Unexpected code " + response);
                }

                if (response.code() == SUCCESSFUL_DOWNLOAD) {
                    Log.i(TAG, "onResponse : SUCCESSFUL_DOWNLOAD");
                    isRequestProblem = false;
                    isResponseProblem = false;
                    ResponseBody in = response.body();
                    InputStream inputStream = in.byteStream();
                    updateImage(BitmapFactory.decodeStream(inputStream));
                } else {
                    isResponseProblem = true;
                    imageView.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "onResponse : response_problem");
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.response_problem));
                        }
                    });
                }
            }
        });
    }

    private void getResponseDetails(Response response) {
        Headers headers = response.headers();
        Log.i(TAG, "Response code: " + String.valueOf(response.code()));
        Log.i(TAG, "Response message: " + response.message());
        Log.i(TAG, "Protocol: " + response.protocol());
        Log.i(TAG, "Number headers: " + headers.size());
        for (int i = 0; i < headers.size(); i++) {
            Log.i(TAG, "i: " + i + " : " + headers.name(i) + "=" + headers.value(i));
        }
    }

    private void updateImage(final Bitmap bitmap) {
        Log.i(TAG, "Updating image");
        imageView.post(new Runnable() {
            @Override
            public void run() {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    isImageDisplaying = true;
                } else {
                    imageView.setImageDrawable(getResources()
                            .getDrawable(R.drawable.ic_launcher));
                }
            }
        });
    }

    public boolean isOnline() {
//        check if there is an internet connection
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
