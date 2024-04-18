package com.example.newsapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.BuildConfig;
import com.example.newsapp.MainActivity;
import com.example.newsapp.data.ArticleModel;
import com.example.newsapp.R;
import com.example.newsapp.data.NewsApiResponse;
import com.example.newsapp.data.NewsApiService;
import com.example.newsapp.data.RetrofitClient;
import com.example.newsapp.standardnews.StandardNewsRecyclerAdapter;
import com.example.newsapp.topnews.TopStoriesRecyclerAdapter;
import com.example.newsapp.utility.NewsItemClickListener;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {
    ImageView newsFragmentImage;
    TextView newsFragmentTitle;
    TextView newsFragmentDescription;
    AppCompatButton closeFragment;

    RecyclerView newsFragmentRecycler;
    NewsApiService newsService;
    StandardNewsRecyclerAdapter standardNewsAdapter;

    private ArticleModel article;

    public NewsFragment() {

    }

    public NewsFragment(ArticleModel article) {
        this.article = article;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_standard_news, container, false);

        newsService = RetrofitClient.getClient().create(NewsApiService.class);
        fetchNewsData();

        newsFragmentImage = view.findViewById(R.id.newsFragmentImage);
        newsFragmentTitle = view.findViewById(R.id.newsFragmentTitle);
        newsFragmentDescription = view.findViewById(R.id.newsFragmentDescription);
        closeFragment = view.findViewById(R.id.closeFragmentButton);
        closeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFragment(view);
            }
        });

        if (article != null) {
            newsFragmentTitle.setText(article.getTitle());
            newsFragmentDescription.setText(article.getDescription());
            Picasso.get().load(article.getUrlToImage()).into(newsFragmentImage);
        }

        newsFragmentRecycler = view.findViewById(R.id.newsFragmentRecycler);
        LinearLayoutManager standardNewsLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        newsFragmentRecycler.setLayoutManager(standardNewsLayoutManager);


        return view;
    }

    public void closeFragment(View view) {
        view.setVisibility(View.GONE);
    }

    private void fetchNewsData() {
        String newsApiKey = BuildConfig.NEWS_API_KEY;
        Call<NewsApiResponse> topStories = newsService.getArticlesEverything("australia", newsApiKey);
        topStories.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                if (response.isSuccessful()) {
                    standardNewsAdapter = new StandardNewsRecyclerAdapter(response.body(), new NewsItemClickListener() {
                        @Override
                        public void onNewsItemClick(ArticleModel article) {
                            return;
                        }
                    });
                    newsFragmentRecycler.setAdapter(standardNewsAdapter);

                } else {
                    Log.e("MainActivity", "Failed to fetch article data on response: " + response.message());
                    Toast.makeText(getContext(), "Failed to fetch news articles", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable throwable) {
                Log.e("MainActivity", "Failed to fetch article data on failure" + throwable.getMessage());
                Toast.makeText(getContext(), "Failed to fetch news articles", Toast.LENGTH_SHORT).show();
            }
        });
    }
}