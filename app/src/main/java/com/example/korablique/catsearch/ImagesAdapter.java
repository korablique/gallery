package com.example.korablique.catsearch;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.korablique.catsearch.imagesearch.ImageInfo;

import java.util.ArrayList;
import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private Context context;
    private List<ImageInfo> images = new ArrayList<>();

    public ImagesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LinearLayout item = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.images_recyclerview_item, parent, false);
        return new ImageViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        ViewGroup item = holder.getItem();
        ImageView imageView = item.findViewById(R.id.image_view);
        Glide.with(context).load(images.get(position).getThumbnailUrl()).into(imageView);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void addItems(List<ImageInfo> images) {
        this.images.addAll(images);
        notifyDataSetChanged();
    }
}
