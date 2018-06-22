package com.example.korablique.gallery;


import com.example.korablique.gallery.imagesearch.JSONResponse;

import retrofit2.Callback;

import static com.example.korablique.gallery.imagesearch.BingSearchConstants.SEARCH_QUERY;

public class MainActivityModelImpl implements MainActivityModel {
    @Override
    public void requestImages(Callback<JSONResponse> callback) {
        GalleryApplication.getApi().getData(SEARCH_QUERY).enqueue(callback);
    }
}
