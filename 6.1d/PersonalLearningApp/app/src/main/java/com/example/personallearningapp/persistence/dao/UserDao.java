package com.example.personallearningapp.persistence.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.personallearningapp.models.User;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    public ListenableFuture<List<User>> getAll();

    @Query("SELECT * FROM user WHERE uid = :uid")
    public ListenableFuture<User> getUserById(int uid);

    @Query("SELECT * FROM user WHERE username = :username")
    public ListenableFuture<User> getUserByUsername(String username);

    @Query("SELECT * FROM User WHERE email = :email")
    public ListenableFuture<User> getUserByEmail(String email);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public ListenableFuture<Void> insert(User user);

    @Delete
    public ListenableFuture<Void> delete(User user);

    @Query("UPDATE user SET question_count = :questionCount WHERE uid = :uid")
    public ListenableFuture<Void> updateQuestionCount(int uid, int questionCount);

    @Query("UPDATE user SET correct_question_count = :correctQuestionCount WHERE uid = :uid")
    public ListenableFuture<Void> updateCorrectQuestionCount(int uid, int correctQuestionCount);

    @Query("UPDATE user SET incorrect_question_count = :incorrectQuestionCount WHERE uid = :uid")
    public ListenableFuture<Void> updateIncorrectQuestionCount(int uid, int incorrectQuestionCount);
}
