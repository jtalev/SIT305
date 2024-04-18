package com.example.newsapp.standardnews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.data.ArticleModel;
import com.example.newsapp.data.NewsApiResponse;
import com.example.newsapp.utility.NewsItemClickListener;
import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StandardNewsRecyclerAdapter extends RecyclerView.Adapter<StandardNewsRecyclerAdapter.ViewHolder> {

    private List<ArticleModel> articleList;
    private NewsItemClickListener listener;

    public StandardNewsRecyclerAdapter(NewsApiResponse response, NewsItemClickListener listener) {
        articleList = response.getArticles();
        this.listener = listener;
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView standardNewsImage;
        TextView standardNewsTitle;
        TextView standardNewsDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            standardNewsImage = itemView.findViewById(R.id.standardNewsImage);
            standardNewsTitle = itemView.findViewById(R.id.standardNewsTitle);
            standardNewsDescription = itemView.findViewById(R.id.standardNewsDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ArticleModel clickedArticle = articleList.get(position);
                        listener.onNewsItemClick(clickedArticle);
                    }
                }
            });
        }
    }
}
