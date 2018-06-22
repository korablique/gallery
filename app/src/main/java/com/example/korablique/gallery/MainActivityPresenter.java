package com.example.korablique.gallery;


import android.os.Bundle;

public interface MainActivityPresenter {
    void onActivityCreate(Bundle savedInstanceState);
    void onActivitySaveInstanceState(Bundle outState);
    void onActivityDestroy();
}
