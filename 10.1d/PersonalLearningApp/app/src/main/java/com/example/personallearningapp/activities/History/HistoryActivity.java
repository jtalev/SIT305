package com.example.personallearningapp.activities.History;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.example.personallearningapp.R;
import com.example.personallearningapp.activities.Profile.ProfileActivity;
import com.example.personallearningapp.models.PastQuiz;
import com.example.personallearningapp.models.QuizHistory;
import com.example.personallearningapp.models.User;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    User user;
    RecyclerView recyclerView;
    HistoryContainerAdapter historyContainerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            user = getIntent().getSerializableExtra("USER", User.class);
        }

        PastQuiz pastQuiz1 = new PastQuiz(
                "What is the capital of France?",
                "Paris",
                "London",
                "Berlin",
                "Paris",
                "Paris"
        );
        PastQuiz pastQuiz2 = new PastQuiz(
                "What is 2 + 2?",
                "3",
                "4",
                "5",
                "3",
                "4"
        );

        List<PastQuiz> pastQuizList = new ArrayList<>();
        pastQuizList.add(pastQuiz1);
        pastQuizList.add(pastQuiz2);

        QuizHistory history = new QuizHistory(pastQuizList);

        user.setHistory(history);

        historyContainerAdapter = new HistoryContainerAdapter(history);
        recyclerView = findViewById(R.id.quiz_history_container);
        this.recyclerView.setAdapter(historyContainerAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }

    public void back(View view) {
        Intent intent = new Intent(HistoryActivity.this, ProfileActivity.class);
        intent.putExtra("USER", user);
        startActivity(intent);
    }
}