package com.example.korablique.catsearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    MainActivityPresenterImpl presenter = new MainActivityPresenterImpl(
            new MainActivityModelImpl(),
            new MainActivityViewImpl(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.onActivityCreate(this);
    }
}
