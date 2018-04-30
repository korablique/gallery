package com.example.korablique.catsearch;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.example.korablique.catsearch.imagesearch.ImageInfo;
import com.example.korablique.catsearch.imagesearch.JSONResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.korablique.catsearch.imagesearch.BingSearchConstants.SEARCH_QUERY;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.images_recycler_view);
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        final ImagesAdapter adapter = new ImagesAdapter(this);
        recyclerView.setAdapter(adapter);

        CatSearchApplication.getApi().getData(SEARCH_QUERY).enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(@NonNull Call<JSONResponse> call, @NonNull Response<JSONResponse> response) {
                if (response.body() != null) {
                    List<ImageInfo> imageInfoList = response.body().getImageInfoList();
                    adapter.addItems(imageInfoList);
                }
            }
            @Override
            public void onFailure(@NonNull Call<JSONResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
