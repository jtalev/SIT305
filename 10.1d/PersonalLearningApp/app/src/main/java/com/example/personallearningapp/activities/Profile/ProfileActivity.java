package com.example.personallearningapp.activities.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.personallearningapp.R;
import com.example.personallearningapp.activities.History.HistoryActivity;
import com.example.personallearningapp.activities.Task.TaskActivity;
import com.example.personallearningapp.activities.Upgrade.UpgradeActivity;
import com.example.personallearningapp.models.User;

public class ProfileActivity extends AppCompatActivity {

    User user;
    TextView username;
    TextView email;
    TextView totalQuestions;
    TextView correctQuestions;
    TextView incorrectQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            user = getIntent().getSerializableExtra("USER", User.class);
        }

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        totalQuestions = findViewById(R.id.total_questions);
        correctQuestions = findViewById(R.id.correct_questions);
        incorrectQuestions = findViewById(R.id.incorrect_questions);

        username.setText(user.getUsername());
        email.setText(user.getEmail());

        totalQuestions.setText(String.valueOf(user.getQuestionCount()));
        correctQuestions.setText(String.valueOf(user.getCorrectQuestionCount()));
        incorrectQuestions.setText(String.valueOf(user.getIncorrectQuestionCount()));
    }

    public void launchTask(View view) {
        Intent intent = new Intent(ProfileActivity.this, TaskActivity.class);
        intent.putExtra("USER", user);
        startActivity(intent);
        finish();
    }

    public void shareProfile(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                  "Check out my learning progress\n" +
                        "Questions Answered: " + String.valueOf(user.getQuestionCount()) +
                        "\nCorrect Answers: " + String.valueOf(user.getCorrectQuestionCount()) +
                        "\nIncorrect Answers: " + String.valueOf(user.getIncorrectQuestionCount()));
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    public void launchUpgrade(View view) {
        Intent intent = new Intent(ProfileActivity.this, UpgradeActivity.class);
        intent.putExtra("USER", user);
        startActivity(intent);
        finish();
    }

    public void launchHistory(View view) {
        Intent intent = new Intent(ProfileActivity.this, HistoryActivity.class);
        intent.putExtra("USER", user);
        startActivity(intent);
        finish();
    }
}