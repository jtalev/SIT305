package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private NewsApiService newsService;

    private NewsApiResponse newsResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsService = RetrofitClient.getClient().create(NewsApiService.class);

        fetchNewsData();
    }

    private void fetchNewsData() {
        String newsApiKey = BuildConfig.NEWS_API_KEY;
        Call<NewsApiResponse> call = newsService.getArticlesEverything("politics", newsApiKey);
        call.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                if (response.isSuccessful()) {
                    newsResponse = response.body();
                } else {
                    Log.e("MainActivity", "Failed to fetch article data on response: " + response.message());
                    Toast.makeText(MainActivity.this, "Failed to fetch news articles", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable throwable) {
                Log.e("MainActivity", "Failed to fetch article data on failure" + throwable.getMessage());
                Toast.makeText(MainActivity.this, "Failed to fetch news articles", Toast.LENGTH_SHORT).show();
            }
        });
    }
}