package com.example.itubeapp.playlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.itubeapp.R;
import com.example.itubeapp.data.playlist.PlaylistItem;
import com.example.itubeapp.utils.ViewModelUtils;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public class MyPlaylistActivity extends AppCompatActivity {
    ImageButton backButton;
    RecyclerView playlistRecycler;
    MyPlaylistRecyclerAdapter playlistRecyclerAdapter;
    ListenableFuture<List<PlaylistItem>> playlistItems;
    MyPlaylistViewModel viewModel;
    int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_playlist);

        uid = getIntent().getIntExtra("UID", -1);
        viewModel = new ViewModelUtils().getViewModel(this, MyPlaylistViewModel.class);
        playlistItems = viewModel.getUserPlaylist(uid, this);


        playlistRecycler = findViewById(R.id.playlist_recycler_view);
        playlistRecyclerAdapter = new MyPlaylistRecyclerAdapter(playlistItems);
        LinearLayoutManager playlistRecyclerLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        playlistRecycler.setLayoutManager(playlistRecyclerLayoutManager);
        playlistRecycler.setAdapter(playlistRecyclerAdapter);

        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}