package com.example.taskmanager.ui.tasks;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.taskmanager.persistence.AppDatabase;
import com.example.taskmanager.persistence.Task;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public class TaskViewModel extends ViewModel {

    private AppDatabase appDb;

    public TaskViewModel() {

    }

    public AppDatabase getAppDb(Context context) {
        if (appDb == null) {
            appDb = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "AppDatabase").build();
        }

        return appDb;
    }

    public ListenableFuture<List<Task>> getAllOrderedByDueDate(Context context) {
        return getAppDb(context).getTaskDao().getAllOrderedByDueDate();
    }

    public ListenableFuture<Integer> deleteAllTasks(Context context) {
        return getAppDb(context).getTaskDao().deleteAllTasks();
    }
}