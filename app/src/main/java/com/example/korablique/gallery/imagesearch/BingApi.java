package com.example.korablique.gallery.imagesearch;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static com.example.korablique.gallery.imagesearch.BingSearchConstants.PATH;
import static com.example.korablique.gallery.imagesearch.BingSearchConstants.SUBSCRIPTION_KEY_NAME;
import static com.example.korablique.gallery.imagesearch.BingSearchConstants.SUBSCRIPTION_KEY_VALUE;

public interface BingApi {
    @Headers(SUBSCRIPTION_KEY_NAME + ": " + SUBSCRIPTION_KEY_VALUE)
    @GET(PATH)
    Call<JSONResponse> getData(@Query("q") String searchQuery);
}
