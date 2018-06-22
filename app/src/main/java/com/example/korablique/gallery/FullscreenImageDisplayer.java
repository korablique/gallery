package com.example.korablique.gallery;


import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.util.List;

public class FullscreenImageDisplayer {
    private Activity activity;
    @Nullable
    private ImageViewer imageViewer;
    private GenericDraweeHierarchyBuilder hierarchyBuilder;
    private ImageViewer.OnImageChangeListener onImageChangeListener;
    private ImageViewer.OnDismissListener onDismissListener;
    private View overlayView;

    public FullscreenImageDisplayer(Activity activity,
                                    GenericDraweeHierarchyBuilder hierarchyBuilder,
                                    ImageViewer.OnImageChangeListener onImageChangeListener,
                                    ImageViewer.OnDismissListener onDismissListener,
                                    View overlayView) {
        this.activity = activity;
        this.hierarchyBuilder = hierarchyBuilder;
        this.onImageChangeListener = onImageChangeListener;
        this.onDismissListener = onDismissListener;
        this.overlayView = overlayView;
    }

    public void display(
            List<String> imagesURLs,
            int position) {
        // remove parent because overlayView could already have parent
        if (overlayView.getParent() != null) {
            ((ViewGroup) overlayView.getParent()).removeView(overlayView);
        }
        imageViewer = new ImageViewer.Builder(activity, imagesURLs)
                .setStartPosition(position)
                .hideStatusBar(false)
                .setCustomDraweeHierarchyBuilder(hierarchyBuilder)
                .setImageChangeListener(onImageChangeListener)
                .setOnDismissListener(onDismissListener)
                .setOverlayView(overlayView)
                .show();
    }

    public void destroy() {
        if (imageViewer != null) {
            imageViewer.onDismiss();
        }
    }
}
