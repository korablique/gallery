package com.example.korablique.catsearch;


import android.os.Bundle;

public interface MainActivityPresenter {
    void onActivityCreate(Bundle savedInstanceState);
    void onActivitySaveInstanceState(Bundle outState);
    void onActivityDestroy();
}
