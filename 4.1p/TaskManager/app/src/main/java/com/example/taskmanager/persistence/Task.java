package com.example.taskmanager.persistence;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "task")
    public String task;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "due_date")
    public Date dueDate;

    @ColumnInfo(name = "is_complete")
    public boolean isComplete;
}
