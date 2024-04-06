package com.example.taskmanager.ui.editTask;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.taskmanager.persistence.AppDatabase;
import com.example.taskmanager.persistence.Task;
import com.google.common.util.concurrent.ListenableFuture;

public class EditTaskViewModel extends ViewModel {
    AppDatabase appDb;

    public EditTaskViewModel() {

    }

    public AppDatabase getAppDb(Context context) {
        if (appDb == null) {
            appDb = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "AppDatabase").build();
        }

        return appDb;
    }

    public ListenableFuture<Integer> updateTask(Task task, Context context) {
        return getAppDb(context).getTaskDao().updateTask(task);
    }
}
