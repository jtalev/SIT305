package com.example.personallearningapp.activities.Quiz;

import android.content.Context;

import com.example.personallearningapp.models.User;
import com.example.personallearningapp.persistence.AppDatabase;
import com.google.common.util.concurrent.ListenableFuture;

public class FinishQuizViewModel {

    private final AppDatabase appDb;

    public FinishQuizViewModel (Context context) {
        this.appDb = AppDatabase.getInstance(context);
    }

    public ListenableFuture<Void> updateQuestionCount(int uid, int questionCount) {
        return appDb.getUserDao().updateQuestionCount(uid, questionCount);
    }

    public ListenableFuture<Void> updateCorrectQuestionCount(int uid, int correctQuestionCount) {
        return appDb.getUserDao().updateCorrectQuestionCount(uid, correctQuestionCount);
    }

    public ListenableFuture<Void> updateIncorrectQuestionCount(int uid, int incorrectQuestionCount) {
        return appDb.getUserDao().updateIncorrectQuestionCount(uid, incorrectQuestionCount);
    }

    public ListenableFuture<User> getUserById(int uid) {
        return appDb.getUserDao().getUserById(uid);
    }
}
