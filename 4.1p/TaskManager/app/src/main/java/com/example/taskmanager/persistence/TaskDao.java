package com.example.taskmanager.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    public ListenableFuture<List<Task>> getAll();

    @Query("SELECT * FROM task ORDER BY due_date")
    public ListenableFuture<List<Task>> getAllOrderedByDueDate();

    @Query("SELECT * FROM task Where task_id = :taskId")
    public LiveData<Task> getTaskById(int taskId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public ListenableFuture<Void> insertTask(Task task);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public ListenableFuture<Integer> updateTask(Task task);

    @Delete
    public ListenableFuture<Integer> deleteTask(Task task);

    @Query("DELETE FROM task")
    public ListenableFuture<Integer> deleteAllTasks();
}
