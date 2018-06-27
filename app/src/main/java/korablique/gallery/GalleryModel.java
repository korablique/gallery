package korablique.gallery;


import korablique.gallery.imagesearch.JSONResponse;
import retrofit2.Callback;

public interface GalleryModel {
    void requestImages(Callback<JSONResponse> callback);
}
