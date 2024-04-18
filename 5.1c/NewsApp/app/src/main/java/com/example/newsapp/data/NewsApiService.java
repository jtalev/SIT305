package com.example.newsapp.data;

import com.example.newsapp.data.NewsApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    @GET("everything")
    Call<NewsApiResponse> getArticlesEverything(
            @Query("q") String query,
            @Query("apiKey") String apiKey
    );

    @GET("top-headlines")
    Call<NewsApiResponse> getTopHeadlines(
            @Query("country") String twoLetterCountryCode,
            @Query("apiKey") String apiKey
    );
}
