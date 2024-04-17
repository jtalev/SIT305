package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.newsapp.standardnews.StandardNewsRecyclerAdapter;
import com.example.newsapp.topnews.TopStoriesRecyclerAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private NewsApiService newsService;
    private RecyclerView topStoriesRecyclerView;
    private RecyclerView newsRecyclerView;
    private TopStoriesRecyclerAdapter topStoriesAdapter;
    private StandardNewsRecyclerAdapter standardNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topStoriesRecyclerView = findViewById(R.id.topStoriesRecyclerView);
        LinearLayoutManager topStoriesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        topStoriesRecyclerView.setLayoutManager(topStoriesLayoutManager);

        newsRecyclerView = findViewById(R.id.newsRecyclerView);
        GridLayoutManager standardNewsLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        newsRecyclerView.setLayoutManager(standardNewsLayoutManager);

        newsService = RetrofitClient.getClient().create(NewsApiService.class);

        fetchNewsData();
    }

    private void fetchNewsData() {
        String newsApiKey = BuildConfig.NEWS_API_KEY;
        Call<NewsApiResponse> topStories = newsService.getArticlesEverything("australia", newsApiKey);
        topStories.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                if (response.isSuccessful()) {
                    topStoriesAdapter = new TopStoriesRecyclerAdapter(response.body());
                    topStoriesRecyclerView.setAdapter(topStoriesAdapter);

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

        Call<NewsApiResponse> newsArticles = newsService.getArticlesEverything("world", newsApiKey);
        newsArticles.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                if (response.isSuccessful()) {
                    standardNewsAdapter = new StandardNewsRecyclerAdapter(response.body());
                    newsRecyclerView.setAdapter(standardNewsAdapter);

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