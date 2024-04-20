package com.example.itubeapp.data.playlist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Playlist_Item")
public class PlaylistItem {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "uid")
    private int uid;

    @ColumnInfo(name = "playlist_item")
    private String youtubeUrl;

    public PlaylistItem() {}

    public PlaylistItem(int _uid, String _youtubeUrl) {
        this.uid = _uid;
        this.youtubeUrl = _youtubeUrl;
    }

    public int getId() {return id;}
    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {return uid;}
    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getYoutubeUrl() {return youtubeUrl;}
    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }
}
