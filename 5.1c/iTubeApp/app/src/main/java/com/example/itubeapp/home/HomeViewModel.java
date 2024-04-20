package com.example.itubeapp.home;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.itubeapp.data.AppDatabase;
import com.example.itubeapp.data.playlist.PlaylistItem;
import com.google.common.util.concurrent.ListenableFuture;

public class HomeViewModel extends ViewModel {
    private AppDatabase appDb;

    public AppDatabase getAppDb(Context context) {
        if (appDb == null) {
            appDb = Room.databaseBuilder(context, AppDatabase.class, "AppDatabase").build();
        }
        return appDb;
    }

    public ListenableFuture<Void> insertPlaylistItem(PlaylistItem playlistItem, Context context) {
        return getAppDb(context).getPlaylistItemDao().insertPlaylistItem(playlistItem);
    }
}
