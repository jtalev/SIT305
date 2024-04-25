package com.example.personallearningapp.persistence.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

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
}
