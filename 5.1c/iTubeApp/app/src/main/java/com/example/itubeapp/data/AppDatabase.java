package com.example.itubeapp.data;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.itubeapp.data.playlist.PlaylistItem;
import com.example.itubeapp.data.playlist.PlaylistItemDao;
import com.example.itubeapp.data.user.User;
import com.example.itubeapp.data.user.UserDao;

@Database(entities = {User.class, PlaylistItem.class}, version=3, autoMigrations = {@AutoMigration(from = 2, to = 3)})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
    public abstract PlaylistItemDao getPlaylistItemDao();
}
