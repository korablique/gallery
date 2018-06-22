package com.example.korablique.gallery;

import android.app.Application;

import com.example.korablique.gallery.imagesearch.BingApi;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.korablique.gallery.imagesearch.BingSearchConstants.HOST;


public class GalleryApplication extends Application {
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

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                .setResizeAndRotateEnabledForNetwork(true)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
    }

    public static BingApi getApi() {
        return bingApi;
    }
}
