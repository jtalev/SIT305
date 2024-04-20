package com.example.itubeapp.data.playlist;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface PlaylistItemDao {
    @Query("SELECT * FROM Playlist_Item WHERE uid = :uid")
    public ListenableFuture<List<PlaylistItem>> getUserPlaylist(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public ListenableFuture<Void> insertPlaylistItem(PlaylistItem playlistItem);

}
