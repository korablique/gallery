package korablique.gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class GalleryActivity extends AppCompatActivity {
    private GalleryPresenterImpl presenter = new GalleryPresenterImpl(
            new GalleryModelImpl(),
            new GalleryViewImpl(this),
            this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.onActivityCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onActivitySaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onActivityDestroy();
    }
}
