package korablique.gallery;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

import korablique.gallery.imagesearch.PixabayAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static korablique.gallery.imagesearch.SearchConstants.HOST;


public class GalleryApplication extends Application {
    private static PixabayAPI pixabayAPI;
    private Retrofit retrofit;
    private static String YANDEX_METRICA_API_KEY = "d332740a-59c8-4c66-a5fb-9884eac210c5";

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
