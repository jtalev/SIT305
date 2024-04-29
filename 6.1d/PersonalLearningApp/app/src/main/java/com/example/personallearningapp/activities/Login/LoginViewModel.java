package com.example.personallearningapp.activities.Login;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.personallearningapp.models.User;
import com.example.personallearningapp.persistence.AppDatabase;
import com.google.common.util.concurrent.ListenableFuture;

public class LoginViewModel extends ViewModel {
    private AppDatabase appDb;

    public LoginViewModel(Context context) {
        this.appDb = AppDatabase.getInstance(context);
    }

    public ListenableFuture<User> getUserByUsername(String username) {
        return appDb.getUserDao().getUserByUsername(username);
    }

    public boolean isUserLoginValid(User user, String username, String password) {
        return username.equals(user.getUsername()) && password.equals((user.getPassword()));
    }
}
