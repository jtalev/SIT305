package com.example.newsapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.http.Url;

public class ArticleModel {

    private Source source;

    public Source getSource() {
        return source;
    }

    public static class Source {
        @SerializedName("name")
        private String name;

        public String getName() {
            return name;
        }
    }

    @SerializedName("author")
    private String author;

    public String getAuthor() {
        return author;
    }

    @SerializedName("title")
    private String title;

    public String getTitle() {
        return title;
    }

    @SerializedName("description")
    private String description;

    public String getDescription() {
        return description;
    }

    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }

    @SerializedName("urlToImage")
    private String urlToImage;

    public String getUrlToImage() {
        return urlToImage;
    }

    @SerializedName("publishedAt")
    private String publishedAt;

    public String getPublishedAt() {
        return publishedAt;
    }

    @SerializedName("content")
    private String content;

    public String getContent() {
        return content;
    }
}
