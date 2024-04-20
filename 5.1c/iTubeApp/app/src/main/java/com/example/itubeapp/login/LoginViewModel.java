package com.example.itubeapp.login;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.itubeapp.data.AppDatabase;
import com.example.itubeapp.data.user.User;
import com.google.common.util.concurrent.ListenableFuture;

public class LoginViewModel extends ViewModel {
    private AppDatabase appDb;

    public AppDatabase getAppDb(Context context) {
        if(appDb == null) {
            appDb = Room.databaseBuilder(context, AppDatabase.class, "AppDatabase").build();
        }

        return appDb;
    }

    public ListenableFuture<User> getUserByUsernamePassword(String username, String password, Context context) {
        return getAppDb(context).getUserDao().getUserByUsernamePassword(username, password);
    }
}
