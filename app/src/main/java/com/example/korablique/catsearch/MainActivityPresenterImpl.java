package com.example.korablique.catsearch;


import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.korablique.catsearch.imagesearch.ImageInfo;
import com.example.korablique.catsearch.imagesearch.JSONResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenterImpl implements MainActivityPresenter {
    private MainActivityModel model;
    private MainActivityView view;
    private ImagesAdapter adapter;

    public MainActivityPresenterImpl(MainActivityModel model, MainActivityView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onActivityCreate(Activity activity) {
        adapter = new ImagesAdapter(activity);
        view.initActivity(adapter);

        model.requestImages(new Callback<JSONResponse>() {
            @Override
            public void onResponse(@NonNull Call<JSONResponse> call, @NonNull Response<JSONResponse> response) {
                if (response.body() != null) {
                    List<ImageInfo> imageInfoList = response.body().getImageInfoList();
                    adapter.addItems(imageInfoList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<JSONResponse> call, @NonNull Throwable t) {
                view.displayError(t.getMessage());
            }
        });
    }
}
