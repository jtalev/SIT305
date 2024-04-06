package com.example.taskmanager.persistence;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Task")
public class Task {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    public int id;

    @ColumnInfo(name = "task")
    public String task;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "due_date")
    public Date dueDate;

    @ColumnInfo(name = "is_complete")
    public boolean isComplete = false;

    public Task(String task, String description, Date dueDate) {
        this.task = task;
        this.description = description;
        this.dueDate = dueDate;
    }

    public Task() {}

    public int getId() {return id;}
    public String getTask() {return task;}
    public String getDescription() {return description;}
    public Date getDueDate() {return dueDate;}
}
