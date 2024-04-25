package com.example.personallearningapp.persistence.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.personallearningapp.models.UserInterest;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface UserInterestDao {
    @Query("SELECT * FROM user_interest")
    public ListenableFuture<List<UserInterest>> getAllByUid();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public ListenableFuture<Void> insert(UserInterest userInterest);

    @Delete
    public ListenableFuture<Void> delete(UserInterest userInterest);
}
