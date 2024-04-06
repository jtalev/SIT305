package com.example.taskmanager.ui.editTask;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.taskmanager.R;
import com.example.taskmanager.persistence.Task;
import com.example.taskmanager.ui.tasks.TaskViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditTaskActivity extends AppCompatActivity {

    private Button updateButton;
    private TextInputEditText titleTextInput;
    private TextInputEditText descriptionTextInput;
    private TextView dueDateTextView;
    private EditTaskViewModel editTaskViewModel;
    private Button selectDateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        getSupportActionBar().hide();

        titleTextInput = findViewById(R.id.updateTitleTextInput);
        descriptionTextInput = findViewById(R.id.updateDescriptionTextInput);
        dueDateTextView = findViewById(R.id.updateDateTextView);
        updateButton = findViewById(R.id.updateButton);
        selectDateButton = findViewById(R.id.updateDateButton);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String title = extras.getString("title");
            String description = extras.getString("description");
            long dueDateMillis = extras.getLong("dueDate");

            Date dueDate = new Date(dueDateMillis);

            titleTextInput.setText(title);
            descriptionTextInput.setText(description);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String formattedDueDate = sdf.format(dueDate);
            dueDateTextView.setText(formattedDueDate);
        }

        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updateTitle = titleTextInput.getText().toString().trim();
                String updateDescription = descriptionTextInput.getText().toString().trim();
                String formattedDueDate = dueDateTextView.getText().toString().trim();
                Date updatedDueDate = null;
                try {
                    updatedDueDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(formattedDueDate);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Task updatedTask = new Task(updateTitle, updateDescription, updatedDueDate, extras.getInt("id"));

                editTaskViewModel = new ViewModelProvider(EditTaskActivity.this).get(EditTaskViewModel.class);
                editTaskViewModel.updateTask(updatedTask, EditTaskActivity.this);

                finish();
            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
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
}