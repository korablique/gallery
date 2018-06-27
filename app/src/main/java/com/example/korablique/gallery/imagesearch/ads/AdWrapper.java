package com.example.korablique.gallery.imagesearch.ads;


import android.content.Context;

import com.example.korablique.gallery.imagesearch.reporting.EventReporter;
import com.yandex.mobile.ads.AdRequest;
import com.yandex.mobile.ads.AdRequestError;
import com.yandex.mobile.ads.InterstitialAd;
import com.yandex.mobile.ads.InterstitialEventListener;

public class AdWrapper {
    public static final String BLOCK_ID_HORIZONTAL = "R-M-DEMO-400x240-context";
    public static final String BLOCK_ID_VERTICAL = "R-M-DEMO-240x400-context";
    private InterstitialAd interstitialAd;
    private boolean adNeeded;

    public AdWrapper(Context context, String blockId) {
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setBlockId(blockId);
        AdRequest adRequest = AdRequest.builder().build();
        interstitialAd.loadAd(adRequest);
        interstitialAd.setInterstitialEventListener(new InterstitialEventListener.SimpleInterstitialEventListener() {
            @Override
            public void onInterstitialFailedToLoad(AdRequestError adRequestError) {
                EventReporter.report(EventReporter.AD_FAILED_TO_LOAD);
            }

            @Override
            public void onInterstitialLoaded() {
                if (adNeeded) {
                    interstitialAd.show();
                    adNeeded = false;
                }
            }

            @Override
            public void onInterstitialShown() {
                EventReporter.report(EventReporter.AD_SHOWN);
            }
        });
    }

    public void showAd() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
            adNeeded = false;
        } else {
            adNeeded = true;
        }
    }
}
