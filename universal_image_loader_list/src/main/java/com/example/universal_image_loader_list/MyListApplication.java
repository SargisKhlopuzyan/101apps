package com.example.universal_image_loader_list;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by clive on 10-Jun-14.
 * www.101apps.co.za
 */
public class MyListApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        configureDefaultImageLoader(getApplicationContext());
    }

    //    set up your default configuration here
    public static void configureDefaultImageLoader(Context context) {

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }
}