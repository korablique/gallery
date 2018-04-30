package com.example.korablique.catsearch;

import android.app.Application;

import com.example.korablique.catsearch.imagesearch.BingApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.korablique.catsearch.imagesearch.BingSearchConstants.HOST;


public class CatSearchApplication extends Application {
    private static BingApi bingApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        bingApi = retrofit.create(BingApi.class);
    }

    public static BingApi getApi() {
        return bingApi;
    }
}
