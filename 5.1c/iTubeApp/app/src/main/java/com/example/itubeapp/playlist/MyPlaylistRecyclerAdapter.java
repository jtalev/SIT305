package com.example.itubeapp.playlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itubeapp.R;
import com.example.itubeapp.data.playlist.PlaylistItem;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public class MyPlaylistRecyclerAdapter extends RecyclerView.Adapter<MyPlaylistRecyclerAdapter.ViewHolder> {
    private ListenableFuture<List<PlaylistItem>> futurePlaylistItems;
    private List<PlaylistItem> playlistItems;

    public MyPlaylistRecyclerAdapter(ListenableFuture<List<PlaylistItem>> _futurePlaylistItems) {
        this.futurePlaylistItems = _futurePlaylistItems;
        try {
            this.playlistItems = futurePlaylistItems.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public MyPlaylistRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPlaylistRecyclerAdapter.ViewHolder holder, int position) {
        PlaylistItem item = playlistItems.get(position);
        holder.playlistItemText.setText(item.getYoutubeUrl());
    }

    @Override
    public int getItemCount() {
        return playlistItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView playlistItemText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playlistItemText = itemView.findViewById(R.id.playlist_item_textview);
        }
    }
}
