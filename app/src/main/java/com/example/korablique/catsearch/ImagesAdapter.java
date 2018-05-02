package com.example.korablique.catsearch;


import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.korablique.catsearch.imagesearch.ImageInfo;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.util.ArrayList;
import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private Context context;
    private List<ImageInfo> imageInfoList = new ArrayList<>();
    private List<String> fullImagesURLs = new ArrayList<>();

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
        Uri uri = Uri.parse(imageInfoList.get(position).getThumbnailUrl());
        ViewGroup item = holder.getItem();
        SimpleDraweeView draweeView = item.findViewById(R.id.image_view);
        draweeView.setImageURI(uri);

        GenericDraweeHierarchyBuilder hierarchyBuilder = GenericDraweeHierarchyBuilder.newInstance(context.getResources())
                .setFailureImage(R.drawable.ic_failure_image, ScalingUtils.ScaleType.FIT_CENTER)
                .setProgressBarImage(R.drawable.ic_loading_white);

        draweeView.setOnClickListener((view) -> {
            View overlayView = LayoutInflater.from(context).inflate(R.layout.overlay_view_layout, null);
            new ImageViewer.Builder(context, fullImagesURLs)
                    .setStartPosition(position)
                    .hideStatusBar(false)
                    .setCustomDraweeHierarchyBuilder(hierarchyBuilder)
                    .setImageChangeListener(getImageChangeListener(overlayView))
                    .setOverlayView(overlayView)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return imageInfoList.size();
    }

    public void addItems(List<ImageInfo> images) {
        this.imageInfoList.addAll(images);
        notifyDataSetChanged();

        fullImagesURLs = new ArrayList<>();
        for (ImageInfo imageInfo : imageInfoList) {
            fullImagesURLs.add(imageInfo.getContentUrl());
        }
    }

    private ImageViewer.OnImageChangeListener getImageChangeListener(View overlayView) {
        return position -> {
            ((TextView) overlayView.findViewById(R.id.image_number_textview))
                    .setText(context.getString(R.string.image_number, position + 1, getItemCount()));
        };
    }
}
