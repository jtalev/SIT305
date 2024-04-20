package com.example.itubeapp.playlist;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.itubeapp.data.AppDatabase;
import com.example.itubeapp.data.playlist.PlaylistItem;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public class MyPlaylistViewModel extends ViewModel {
    private AppDatabase appDb;

    public AppDatabase getAppDb(Context context) {
        if (appDb == null) {
            appDb = Room.databaseBuilder(context, AppDatabase.class, "AppDatabase").build();
        }
        return appDb;
    }

    public ListenableFuture<List<PlaylistItem>> getUserPlaylist(int uid, Context context) {
        return getAppDb(context).getPlaylistItemDao().getUserPlaylist(uid);
    }
}
