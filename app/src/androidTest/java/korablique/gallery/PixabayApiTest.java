package korablique.gallery;


import android.support.annotation.NonNull;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.webkit.URLUtil;

import korablique.gallery.imagesearch.Hit;
import korablique.gallery.imagesearch.JSONResponse;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static korablique.gallery.imagesearch.SearchConstants.SEARCH_QUERY;
import static korablique.gallery.imagesearch.SearchConstants.SUBSCRIPTION_KEY_VALUE;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PixabayApiTest {
    @Test
    public void getDataTest() throws InterruptedException {
        final CountDownLatch mutex = new CountDownLatch(1);
        final List<Hit> hitsList = new ArrayList<>();
        GalleryApplication.getApi().getData(SUBSCRIPTION_KEY_VALUE, SEARCH_QUERY).enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(@NonNull Call<JSONResponse> call, @NonNull Response<JSONResponse> response) {
                hitsList.addAll(response.body().getHits());
                mutex.countDown();
            }
            @Override
            public void onFailure(@NonNull Call<JSONResponse> call, @NonNull Throwable t) {
                mutex.countDown();
            }
        });
        mutex.await();

        Assert.assertFalse(hitsList.isEmpty());
        for (Hit hit : hitsList) {
            Assert.assertTrue(URLUtil.isValidUrl(hit.getLargeImageURL()));
            Assert.assertTrue(URLUtil.isValidUrl(hit.getPreviewURL()));
        }
    }
}
