package com.example.android_recyclerview_and_picasso;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created using Android Studio (Beta) 0.8.14
 * www.101apps.co.za
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    List<AnArticle> data = Collections.emptyList();
    private LayoutInflater inflater;

    public MyRecyclerViewAdapter(Context context, List<AnArticle> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.row_layout, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        AnArticle current = data.get(i);
        Uri uri = Uri.parse(current.imageUrl);
        Log.e("LOG_TAG", "uri: " + uri);
        Context context = myViewHolder.articleImage.getContext();
        Picasso.with(context)
                .load(uri)
                .error(R.drawable.failed)
                .placeholder(R.drawable.response_problem)
                .into(myViewHolder.articleImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView articleImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            articleImage = (ImageView) itemView.findViewById(R.id.imageView2);
            itemView.setOnClickListener(MainActivity.myOnClickListener);
        }
    }
}


