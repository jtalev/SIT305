package com.example.itubeapp.data.user;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.google.common.util.concurrent.ListenableFuture;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    public ListenableFuture<User> getUserByUsernamePassword(String username, String password);

    @Query("SELECT * FROM User WHERE username = :username")
    public ListenableFuture<User> getUserByUsername(String username);

    @Query("SELECT * FROM User WHERE uid = :uid")
    public ListenableFuture<User> getUserByUid(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public ListenableFuture<Void> insertUser(User user);
}
