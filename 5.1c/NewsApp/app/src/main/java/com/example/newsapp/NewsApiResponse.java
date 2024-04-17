package com.example.newsapp;

import com.google.gson.annotations.SerializedName;

public class NewsApiResponse {

    @SerializedName("articles")
    private ArticleModel[] articles;

    public ArticleModel[] getArticles() {
        return articles;
    }
}
