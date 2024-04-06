package com.example.taskmanager.ui.addTask;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.example.taskmanager.persistence.AppDatabase;
import com.example.taskmanager.persistence.Task;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.Date;


public class AddTaskViewModel extends ViewModel {

    private AppDatabase appDb;

    public AddTaskViewModel() {

    }

    private AppDatabase getAppDb(Context context) {
        if (appDb == null) {
            appDb = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "AppDatabase").build();
        }
        return appDb;
    }

    public ListenableFuture<Void> insertTask(String title, String description, Date dueDate, Context context) {
        final Task task = new Task(title, description, dueDate);
        return getAppDb(context).getTaskDao().insertTask(task);
    }
}
