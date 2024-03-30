package com.example.taskmanager.ui.addTask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.taskmanager.databinding.FragmentAddTaskBinding;

public class AddTaskFragment extends Fragment {

    private FragmentAddTaskBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddTaskViewModel addTaskViewModel =
                new ViewModelProvider(this).get(AddTaskViewModel.class);

        binding = FragmentAddTaskBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}