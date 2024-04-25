package com.example.personallearningapp.persistence;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.personallearningapp.models.User;
import com.example.personallearningapp.models.UserInterest;
import com.example.personallearningapp.persistence.dao.UserDao;
import com.example.personallearningapp.persistence.dao.UserInterestDao;

@Database(entities = {User.class, UserInterest.class}, version=1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract UserDao getUserDao();
    public abstract UserInterestDao getUserInterestDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
