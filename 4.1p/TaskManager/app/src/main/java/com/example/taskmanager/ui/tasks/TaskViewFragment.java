package com.example.taskmanager.ui.tasks;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.databinding.FragmentTasksBinding;
import com.example.taskmanager.persistence.Task;
import com.example.taskmanager.ui.addTask.AddTaskViewModel;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public class TaskViewFragment extends Fragment {

    private FragmentTasksBinding binding;
    private RecyclerView taskRecyclerView;
    private TaskRecyclerViewAdapter taskRecyclerViewAdapter;
    private ListenableFuture<List<Task>> taskList;
    private TaskViewModel taskViewModel;
    private Button deleteAllButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        taskViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())).get(TaskViewModel.class);
        taskList = taskViewModel.getAllOrderedByDueDate(requireContext());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TaskViewModel taskViewModel =
                new ViewModelProvider(this).get(TaskViewModel.class);

        binding = FragmentTasksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        taskRecyclerView = binding.taskRecyclerView;
        taskRecyclerViewAdapter = new TaskRecyclerViewAdapter(taskList, requireContext(), this);
        taskRecyclerView.setAdapter(taskRecyclerViewAdapter);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        deleteAllButton = binding.deleteAllButton;

        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllTasks();
            }
        });

        return root;
    }

    public void logTaskList(LiveData<List<Task>> taskList) {
        if (taskList == null) {
            Log.d("TaskList", "TaskList is null");
        } else {
            Log.d("TaskList", taskList.toString());
        }
    }

    public void deleteAllTasks() {
        taskViewModel.deleteAllTasks(requireContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}