package com.example.taskmanager.ui.editTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.taskmanager.R;
import com.example.taskmanager.persistence.Task;
import com.example.taskmanager.ui.tasks.TaskViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditTaskActivity extends AppCompatActivity {

    private Button updateButton;
    private TextInputEditText titleTextInput;
    private TextInputEditText descriptionTextInput;
    private TextView dueDateTextView;
    private EditTaskViewModel editTaskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        getSupportActionBar().hide();

        titleTextInput = findViewById(R.id.updateTitleTextInput);
        descriptionTextInput = findViewById(R.id.updateDescriptionTextInput);
        dueDateTextView = findViewById(R.id.updateDateTextView);
        updateButton = findViewById(R.id.updateButton);

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
}