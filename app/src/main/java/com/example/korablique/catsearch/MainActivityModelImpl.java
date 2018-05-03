package com.example.korablique.catsearch;


import com.example.korablique.catsearch.imagesearch.JSONResponse;

import retrofit2.Callback;

import static com.example.korablique.catsearch.imagesearch.BingSearchConstants.SEARCH_QUERY;

public class MainActivityModelImpl implements MainActivityModel {
    @Override
    public void requestImages(Callback<JSONResponse> callback) {
        CatSearchApplication.getApi().getData(SEARCH_QUERY).enqueue(callback);
    }
}
