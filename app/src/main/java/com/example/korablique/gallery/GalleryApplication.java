package com.example.korablique.gallery;

import android.app.Application;

import com.example.korablique.gallery.imagesearch.PixabayAPI;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.korablique.gallery.imagesearch.SearchConstants.HOST;


public class GalleryApplication extends Application {
    private static PixabayAPI pixabayAPI;
    private Retrofit retrofit;
    private static String YANDEX_METRICA_API_KEY = "8677bcc3-b8db-40c4-b01f-fa748002743c";

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pixabayAPI = retrofit.create(PixabayAPI.class);

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                .setResizeAndRotateEnabledForNetwork(true)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);

        YandexMetricaConfig.Builder configBuilder = YandexMetricaConfig.newConfigBuilder(YANDEX_METRICA_API_KEY);
        YandexMetrica.activate(getApplicationContext(), configBuilder.build());
        YandexMetrica.enableActivityAutoTracking(this);
    }

    public static PixabayAPI getApi() {
        return pixabayAPI;
    }
}
