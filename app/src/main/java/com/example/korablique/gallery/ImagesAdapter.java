package com.example.korablique.gallery;


import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.korablique.gallery.imagesearch.Hit;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private List<Hit> hitsList = new ArrayList<>();
    private List<String> largeImagesURLs = new ArrayList<>();
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
        Uri uri = Uri.parse(hitsList.get(position).getPreviewURL());
        ViewGroup item = holder.getItem();
        SimpleDraweeView draweeView = item.findViewById(R.id.image_view);
        draweeView.setImageURI(uri);

        draweeView.setOnClickListener((view) -> {
            imageDisplayer.display(largeImagesURLs, position);
        });
    }

    @Override
    public int getItemCount() {
        return hitsList.size();
    }

    public void addItems(List<Hit> hitsList) {
        this.hitsList.addAll(hitsList);
        notifyDataSetChanged();

        largeImagesURLs = new ArrayList<>();
        for (Hit hit : this.hitsList) {
            largeImagesURLs.add(hit.getLargeImageURL());
        }
    }

    public List<Hit> getHitsList() {
        return hitsList;
    }
}
