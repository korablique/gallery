package com.example.korablique.catsearch;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


public class MainActivityViewImpl implements MainActivityView {
    private Activity activity;

    public MainActivityViewImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void initActivity(RecyclerView.Adapter adapter) {
        activity.setContentView(R.layout.activity_main);

        RecyclerView recyclerView = activity.findViewById(R.id.images_recycler_view);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }
}
