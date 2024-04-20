package com.example.itubeapp.signup;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.itubeapp.data.AppDatabase;
import com.example.itubeapp.data.user.User;
import com.google.common.util.concurrent.ListenableFuture;

public class SignupViewModel extends ViewModel {
    private AppDatabase appDb;

    public SignupViewModel() {}

    public AppDatabase getAppDb(Context context) {
        if (appDb == null) {
            appDb = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "AppDatabase").build();
        }

        return appDb;
    }
    public ListenableFuture<Void> insertUser(User user, Context context) {
        return getAppDb(context).getUserDao().insertUser(user);
    }

    public ListenableFuture<User> getUserByUsername(String username, Context context) {
        return getAppDb(context).getUserDao().getUserByUsername(username);
    }
}
