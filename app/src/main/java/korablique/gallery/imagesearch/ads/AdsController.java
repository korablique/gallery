package korablique.gallery.imagesearch.ads;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import korablique.gallery.FullscreenImageDisplayer;

import static korablique.gallery.imagesearch.ads.AdWrapper.BLOCK_ID_HORIZONTAL;
import static korablique.gallery.imagesearch.ads.AdWrapper.BLOCK_ID_VERTICAL;

public class AdsController implements FullscreenImageDisplayer.PreviewClicksObserver {
    public static final int CLICKS_BEFORE_SHOWING_AD = 4;
    public static final String PREVIEW_CLICKED_COUNTER = "PREVIEW_CLICKED_COUNTER";
    private static int previewClickedCounter = 0;
    private AdWrapper adWrapper;

    public AdsController(FullscreenImageDisplayer displayer, Context context) {
        displayer.addPreviewClicksObserver(this);

        int orientation = context.getResources().getConfiguration().orientation;
        String blockID;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            blockID = BLOCK_ID_VERTICAL;
        } else {
            blockID = BLOCK_ID_HORIZONTAL;
        }

        adWrapper = new AdWrapper(context, blockID);
    }

    @Override
    public void onPreviewClicked() {
        ++previewClickedCounter;
        if (previewClickedCounter == CLICKS_BEFORE_SHOWING_AD) {
            adWrapper.showAd();
            previewClickedCounter = 0;
        }
    }

    public void saveState(Bundle outState) {
        outState.putInt(PREVIEW_CLICKED_COUNTER, previewClickedCounter);
    }

    public void restoreState(Bundle savedState) {
        previewClickedCounter = savedState.getInt(PREVIEW_CLICKED_COUNTER);
    }
}
