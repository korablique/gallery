package com.example.korablique.gallery;


import com.example.korablique.gallery.imagesearch.JSONResponse;

import retrofit2.Callback;

public interface MainActivityModel {
    void requestImages(Callback<JSONResponse> callback);
}
