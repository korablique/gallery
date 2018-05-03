package com.example.korablique.catsearch;


import com.example.korablique.catsearch.imagesearch.JSONResponse;

import retrofit2.Callback;

public interface MainActivityModel {
    void requestImages(Callback<JSONResponse> callback);
}
