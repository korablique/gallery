package korablique.gallery;


import android.os.Bundle;

public interface GalleryPresenter {
    void onActivityCreate(Bundle savedInstanceState);
    void onActivitySaveInstanceState(Bundle outState);
    void onActivityDestroy();
}
