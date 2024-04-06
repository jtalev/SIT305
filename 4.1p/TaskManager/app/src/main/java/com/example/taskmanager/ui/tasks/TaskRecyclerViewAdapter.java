package com.example.taskmanager.ui.tasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.R;
import com.example.taskmanager.persistence.Task;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder> {

    private ListenableFuture<List<Task>> taskListFuture;
    private List<Task> taskList;
    private Context context;

    public TaskRecyclerViewAdapter(ListenableFuture<List<Task>> taskListFuture, Context context) {
        this.taskListFuture = taskListFuture;
        this.context = context;

        try {
            this.taskList = taskListFuture.get();
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.task_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.taskTextView.setText(taskList.get(position).task);
        holder.dateTextView.setText(taskList.get(position).dueDate);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskTextView;
        TextView dateTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTextView = itemView.findViewById(R.id.taskTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}
