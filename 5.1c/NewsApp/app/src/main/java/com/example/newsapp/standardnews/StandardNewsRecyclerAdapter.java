package com.example.newsapp.standardnews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.ArticleModel;
import com.example.newsapp.NewsApiResponse;
import com.example.newsapp.R;
import com.example.newsapp.topnews.TopStoriesRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StandardNewsRecyclerAdapter extends RecyclerView.Adapter<StandardNewsRecyclerAdapter.ViewHolder> {

    private List<ArticleModel> articleList;

    public StandardNewsRecyclerAdapter(NewsApiResponse response) {
        articleList = response.getArticles();
    }

    @NonNull
    @Override
    public StandardNewsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.standard_news_card, parent, false);
        return new StandardNewsRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StandardNewsRecyclerAdapter.ViewHolder holder, int position) {
        ArticleModel article = articleList.get(position);
        Picasso.get().load(article.getUrlToImage()).into(holder.standardNewsImage);

        holder.standardNewsTitle.setText(article.getTitle());
        holder.standardNewsDescription.setText(article.getDescription());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView standardNewsImage;
        TextView standardNewsTitle;
        TextView standardNewsDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            standardNewsImage = itemView.findViewById(R.id.standardNewsImage);
            standardNewsTitle = itemView.findViewById(R.id.standardNewsTitle);
            standardNewsDescription = itemView.findViewById(R.id.standardNewsDescription);
        }
    }
}
