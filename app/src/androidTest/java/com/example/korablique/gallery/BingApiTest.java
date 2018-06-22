package com.example.korablique.gallery;


import android.support.annotation.NonNull;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.webkit.URLUtil;

import com.example.korablique.gallery.imagesearch.ImageInfo;
import com.example.korablique.gallery.imagesearch.JSONResponse;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.korablique.gallery.imagesearch.BingSearchConstants.SEARCH_QUERY;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BingApiTest {
    @Test
    public void getDataTest() throws InterruptedException {
        final CountDownLatch mutex = new CountDownLatch(1);
        final List<ImageInfo> imageInfoList = new ArrayList<>();
        GalleryApplication.getApi().getData(SEARCH_QUERY).enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(@NonNull Call<JSONResponse> call, @NonNull Response<JSONResponse> response) {
                imageInfoList.addAll(response.body().getImageInfoList());
                mutex.countDown();
            }
            @Override
            public void onFailure(@NonNull Call<JSONResponse> call, @NonNull Throwable t) {
                mutex.countDown();
            }
        });
        mutex.await();

        Assert.assertFalse(imageInfoList.isEmpty());
        for (ImageInfo imageInfo : imageInfoList) {
            Assert.assertTrue(URLUtil.isValidUrl(imageInfo.getContentUrl()));
            Assert.assertTrue(URLUtil.isValidUrl(imageInfo.getThumbnailUrl()));
        }
    }
}
