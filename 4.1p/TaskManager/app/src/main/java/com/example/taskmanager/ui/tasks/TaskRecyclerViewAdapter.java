package com.example.taskmanager.ui.tasks;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.R;
import com.example.taskmanager.persistence.AppDatabase;
import com.example.taskmanager.persistence.Task;
import com.example.taskmanager.ui.editTask.EditTaskActivity;
import com.google.common.util.concurrent.ListenableFuture;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder> {

    private ListenableFuture<List<Task>> taskListFuture;
    private List<Task> taskList;
    private Context context;
    private ViewModelStoreOwner viewModelStoreOwner;

    public TaskRecyclerViewAdapter(ListenableFuture<List<Task>> taskListFuture, Context context, ViewModelStoreOwner viewModelStoreOwner) {
        this.taskListFuture = taskListFuture;
        this.context = context;
        this.viewModelStoreOwner = viewModelStoreOwner;

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
        Task task = taskList.get(position);
        holder.taskTextView.setText(task.task);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = sdf.format(task.dueDate);
        holder.dateTextView.setText("Due date: " + formattedDate);

        Boolean isComplete = task.isComplete;
        if (isComplete == true) {
            holder.markAsDoneButton.setText("Complete");
        }

        holder.markAsDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isComplete = task.isComplete;
                if (isComplete == false) {
                    holder.markAsDoneButton.setText("Complete");
                    task.isComplete = true;
                    updateTask(task);
                } else if (isComplete == true) {
                    holder.markAsDoneButton.setText("Mark as done");
                    task.isComplete = false;
                    updateTask(task);
                }
            }
        });

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditTaskActivity.class);
                intent.putExtra("title", task.task);
                intent.putExtra("description", task.description);
                intent.putExtra("dueDate", task.dueDate.getTime());
                intent.putExtra("id", task.id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public ListenableFuture<Integer> updateTask(Task task) {
        TaskViewModel taskViewModel = new ViewModelProvider(viewModelStoreOwner, ViewModelProvider.AndroidViewModelFactory.getInstance(((Fragment) viewModelStoreOwner).requireActivity().getApplication())).get(TaskViewModel.class);
        return taskViewModel.updateTask(task, context);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskTextView;
        TextView dateTextView;
        Button markAsDoneButton;
        ImageButton editButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTextView = itemView.findViewById(R.id.taskTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            markAsDoneButton = itemView.findViewById(R.id.markAsDoneButton);
            editButton = itemView.findViewById(R.id.editButton);
        }
    }
}
