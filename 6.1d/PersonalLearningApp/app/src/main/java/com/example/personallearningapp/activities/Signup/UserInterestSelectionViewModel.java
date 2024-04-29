package com.example.personallearningapp.activities.Signup;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.personallearningapp.models.UserInterest;
import com.example.personallearningapp.persistence.AppDatabase;
import com.google.common.util.concurrent.ListenableFuture;

public class UserInterestSelectionViewModel extends ViewModel {
    private final AppDatabase appDb;

    public UserInterestSelectionViewModel(Context context) {
        this.appDb = AppDatabase.getInstance(context);
    }

    public ListenableFuture<Void> insert(UserInterest userInterest) {
        return appDb.getUserInterestDao().insert(userInterest);
    }
}
