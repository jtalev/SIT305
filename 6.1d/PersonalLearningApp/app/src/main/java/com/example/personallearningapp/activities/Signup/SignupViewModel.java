package com.example.personallearningapp.activities.Signup;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.personallearningapp.models.User;
import com.example.personallearningapp.persistence.AppDatabase;
import com.google.common.util.concurrent.ListenableFuture;

public class SignupViewModel extends ViewModel {
    private final AppDatabase appDb;
    private final Context context;

    public SignupViewModel (Context context) {
        this.context = context;
        this.appDb = AppDatabase.getInstance(context);
    }

    public ListenableFuture<User> getUserByUsername(String username) {
        return appDb.getUserDao().getUserByUsername(username);
    }

    public ListenableFuture<User> getUserByEmail(String email) {
        return appDb.getUserDao().getUserByEmail(email);
    }

    public ListenableFuture<Void> insert(User user) {
        return appDb.getUserDao().insert(user);
    }
}
