package com.example.korablique.gallery;


import android.os.Bundle;

import com.example.korablique.gallery.imagesearch.ImageInfo;

import java.util.List;

public interface MainActivityView {
    void initActivity();
    void displayConnectivityError();
    void hideConnectivityError();
    void showProgressBar();
    void hideProgressBar();
    void showImages(List<ImageInfo> imageInfoList);
    void destroy();
    void recordState(Bundle outState);
    void restoreState(Bundle savedState);
    boolean hasImages();
}
