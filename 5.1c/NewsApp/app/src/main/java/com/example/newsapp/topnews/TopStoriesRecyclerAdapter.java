package com.example.newsapp.topnews;

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

public class TopStoriesRecyclerAdapter extends RecyclerView.Adapter<TopStoriesRecyclerAdapter.ViewHolder> {
    private List<ArticleModel> articleList;
    private NewsItemClickListener listener;

    public TopStoriesRecyclerAdapter(NewsApiResponse response, NewsItemClickListener listener) {
        articleList = response.getArticles();
        this.listener = listener;
    }

    @NonNull
    @Override
    public TopStoriesRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_news_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TopStoriesRecyclerAdapter.ViewHolder holder, int position) {
        ArticleModel article = articleList.get(position);
        Picasso.get().load(article.getUrlToImage()).into(holder.topNewsCardImage);
        holder.topNewsCardTitle.setText(article.getTitle());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView topNewsCardImage;
        TextView topNewsCardTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            topNewsCardImage = itemView.findViewById(R.id.topNewsCardImage);
            topNewsCardTitle = itemView.findViewById(R.id.topNewsCardTitle);

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
