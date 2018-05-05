package com.example.korablique.catsearch;


import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.korablique.catsearch.imagesearch.ImageInfo;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private List<ImageInfo> imageInfoList = new ArrayList<>();
    private List<String> fullImagesURLs = new ArrayList<>();
    private FullscreenImageDisplayer imageDisplayer;

    public ImagesAdapter(FullscreenImageDisplayer imageDisplayer) {
        this.imageDisplayer = imageDisplayer;
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
        Uri uri = Uri.parse(imageInfoList.get(position).getThumbnailUrl());
        ViewGroup item = holder.getItem();
        SimpleDraweeView draweeView = item.findViewById(R.id.image_view);
        draweeView.setImageURI(uri);

        draweeView.setOnClickListener((view) -> {
            imageDisplayer.display(fullImagesURLs, position);
        });
    }

    @Override
    public int getItemCount() {
        return imageInfoList.size();
    }

    public void addItems(List<ImageInfo> imageInfoList) {
        this.imageInfoList.addAll(imageInfoList);
        notifyDataSetChanged();

        fullImagesURLs = new ArrayList<>();
        for (ImageInfo imageInfo : this.imageInfoList) {
            fullImagesURLs.add(imageInfo.getContentUrl());
        }
    }

    public List<ImageInfo> getImageInfoList() {
        return imageInfoList;
    }
}
