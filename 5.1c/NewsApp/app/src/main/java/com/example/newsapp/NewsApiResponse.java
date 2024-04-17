package com.example.newsapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsApiResponse {

    @SerializedName("articles")
    private List<ArticleModel> articles;

    public List<ArticleModel> getArticles() {
        return articles;
    }
}
