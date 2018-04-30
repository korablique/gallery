package com.example.korablique.catsearch;


import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class ImageViewHolder extends RecyclerView.ViewHolder {
    private ViewGroup item;

    public ImageViewHolder(ViewGroup itemView) {
        super(itemView);
        item = itemView;
    }

    public ViewGroup getItem() {
        return item;
    }
}
