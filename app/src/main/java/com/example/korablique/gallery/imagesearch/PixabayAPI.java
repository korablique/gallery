package com.example.korablique.gallery.imagesearch;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.korablique.gallery.imagesearch.SearchConstants.PATH;

public interface PixabayAPI {
    @GET(PATH)
    Call<JSONResponse> getData(@Query("key") String key, @Query("q") String searchQuery);
}
