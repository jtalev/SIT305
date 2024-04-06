package com.example.taskmanager.ui.addTask;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.taskmanager.databinding.FragmentAddTaskBinding;
import com.example.taskmanager.persistence.AppDatabase;
import com.example.taskmanager.persistence.Task;
import com.example.taskmanager.persistence.TaskDao;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddTaskFragment extends Fragment{

    private FragmentAddTaskBinding binding;
    private TextView dueDateTextView;
    TextInputEditText titleTextInput;
    TextInputEditText descriptionTextInput;
    Button selectDateButton;
    Button saveButton;
    private AddTaskViewModel addTaskViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addTaskViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())).get(AddTaskViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAddTaskBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        titleTextInput = binding.titleTextInput;
        descriptionTextInput = binding.descriptionTextInput;
        dueDateTextView = binding.dueDateTextView;
        selectDateButton = binding.selectDateButton;
        saveButton = binding.saveButton;

        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });

        return root;
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String selectedDate = sdf.format(calendar.getTime());

                        dueDateTextView.setText(selectedDate);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }

    private void saveTask() {
        String title = titleTextInput.getText().toString();
        String description = descriptionTextInput.getText().toString();
        String dueDateStr = dueDateTextView.getText().toString();

        int duration = Toast.LENGTH_SHORT;

        if (title.isEmpty() || description.isEmpty() || dueDateStr.equals("Due Date")) {
            Toast toast = createToastMessage("Please fill all the fields", duration);
            toast.show();
            return;
        }

        // Parse dueDateStr to Date
        Date dueDate;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            dueDate = sdf.parse(dueDateStr);
        } catch (ParseException e) {
            Toast toast = createToastMessage("Invalid date format", duration);
            toast.show();
            return;
        }

        addTaskViewModel.insertTask(title, description, dueDate, requireContext());
    }

    private Toast createToastMessage(CharSequence message, int duration) {
        return Toast.makeText(this.getContext(), message, duration);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}