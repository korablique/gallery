package korablique.gallery.imagesearch.reporting;


import com.yandex.metrica.YandexMetrica;

public class EventReporter {
    public static final String AD_SHOWN = "AD_SHOWN";
    public static final String AD_FAILED_TO_LOAD = "AD_FAILED_TO_LOAD";

    public static void report(String event) {
        YandexMetrica.reportEvent(event);
    }
}
