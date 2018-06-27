package com.example.korablique.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.korablique.gallery.imagesearch.Hit;
import com.example.korablique.gallery.imagesearch.ads.AdsController;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.util.ArrayList;
import java.util.List;


public class MainActivityViewImpl implements MainActivityView {
    private static final String CURRENT_OPENED_IMAGE_POSITION = "CURRENT_OPENED_IMAGE_POSITION";
    private static final String IMAGE_INFO_LIST = "IMAGE_INFO_LIST";
    private Activity activity;
    private ImagesAdapter adapter;
    @Nullable
    private Integer currentOpenedImagePosition;
    private FullscreenImageDisplayer imageDisplayer;
    private AdsController adsController;


    public MainActivityViewImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void initActivity() {
        activity.setContentView(R.layout.activity_main);

        RecyclerView recyclerView = activity.findViewById(R.id.images_recycler_view);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        GenericDraweeHierarchyBuilder hierarchyBuilder = GenericDraweeHierarchyBuilder.newInstance(activity.getResources())
                .setFailureImage(R.drawable.ic_failure_image, ScalingUtils.ScaleType.FIT_CENTER)
                .setProgressBarImage(R.drawable.ic_loading_white);
        View overlayView = LayoutInflater.from(activity).inflate(R.layout.overlay_view_layout, null);
        imageDisplayer = new FullscreenImageDisplayer(
                activity, hierarchyBuilder, getImageChangeListener(overlayView), getOnDismissListener(), overlayView);

        adsController = new AdsController(imageDisplayer, activity);

        adapter = new ImagesAdapter(imageDisplayer);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayConnectivityError() {
        activity.findViewById(R.id.no_internet_textview).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideConnectivityError() {
        activity.findViewById(R.id.no_internet_textview).setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        activity.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        activity.findViewById(R.id.progress_bar).setVisibility(View.GONE);
    }

    @Override
    public void showImages(List<Hit> hitsList) {
        adapter.addItems(hitsList);
    }

    private void displayFullscreenImages(int position) {
        List<String> imagesURLs = new ArrayList<>();
        for (Hit hit : adapter.getHitsList()) {
            imagesURLs.add(hit.getLargeImageURL());
        }
        imageDisplayer.display(imagesURLs, position);
    }

    @Override
    public void destroy() {
        imageDisplayer.destroy();
    }

    @Override
    public void recordState(Bundle outState) {
        if (currentOpenedImagePosition != null) {
            outState.putInt(CURRENT_OPENED_IMAGE_POSITION, currentOpenedImagePosition);
        }
        outState.putParcelableArrayList(IMAGE_INFO_LIST, new ArrayList<>(adapter.getHitsList()));
        adsController.saveState(outState);
    }

    @Override
    public void restoreState(Bundle savedState) {
        adapter.addItems(savedState.getParcelableArrayList(IMAGE_INFO_LIST));
        if (savedState.containsKey(CURRENT_OPENED_IMAGE_POSITION)) {
            displayFullscreenImages(savedState.getInt(CURRENT_OPENED_IMAGE_POSITION));
        }
        adsController.restoreState(savedState);
    }

    @Override
    public boolean hasImages() {
        return adapter.getItemCount() != 0;
    }

    private ImageViewer.OnImageChangeListener getImageChangeListener(View overlayView) {
        return (position) -> {
            currentOpenedImagePosition = position;
            ((TextView) overlayView.findViewById(R.id.image_number_textview))
                    .setText(activity.getString(R.string.image_number, position + 1, adapter.getItemCount()));
        };
    }

    private ImageViewer.OnDismissListener getOnDismissListener() {
        return () -> {
            currentOpenedImagePosition = null;
        };
    }
}
