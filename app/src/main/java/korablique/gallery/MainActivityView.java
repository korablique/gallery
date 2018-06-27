package korablique.gallery;


import android.os.Bundle;

import java.util.List;

import korablique.gallery.imagesearch.Hit;

public interface MainActivityView {
    void initActivity();
    void displayConnectivityError();
    void hideConnectivityError();
    void showProgressBar();
    void hideProgressBar();
    void showImages(List<Hit> hitsList);
    void destroy();
    void recordState(Bundle outState);
    void restoreState(Bundle savedState);
    boolean hasImages();
}
