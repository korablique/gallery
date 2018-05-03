package com.example.korablique.catsearch;


import android.support.v7.widget.RecyclerView;

public interface MainActivityView {
    void initActivity(RecyclerView.Adapter adapter);
    void displayError(String message);
}
