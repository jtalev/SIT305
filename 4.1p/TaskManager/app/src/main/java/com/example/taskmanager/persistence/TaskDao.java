package com.example.taskmanager.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task ORDER BY due_date")
    List<Task> getAllOrderedByDueDate();

    @Query("SELECT * FROM task Where id = :taskId")
    Task getTaskById(int taskId);

    @Insert
    void insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);
}
