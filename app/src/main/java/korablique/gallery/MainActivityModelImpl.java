package korablique.gallery;


import korablique.gallery.imagesearch.JSONResponse;
import retrofit2.Callback;

import static korablique.gallery.imagesearch.SearchConstants.SEARCH_QUERY;
import static korablique.gallery.imagesearch.SearchConstants.SUBSCRIPTION_KEY_VALUE;

public class MainActivityModelImpl implements MainActivityModel {
    @Override
    public void requestImages(Callback<JSONResponse> callback) {
        GalleryApplication.getApi().getData(SUBSCRIPTION_KEY_VALUE, SEARCH_QUERY).enqueue(callback);
    }
}
