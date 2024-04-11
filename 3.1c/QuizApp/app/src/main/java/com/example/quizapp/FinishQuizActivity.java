package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinishQuizActivity extends AppCompatActivity implements View.OnClickListener {

    TextView congratulationsTextView;
    TextView scoreTextView;
    Button takeNewQuizButton;
    Button finishQuizButton;
    String name;
    String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_quiz);

        congratulationsTextView = findViewById(R.id.congratulationsTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        takeNewQuizButton = findViewById(R.id.takeNewQuizButton);
        finishQuizButton = findViewById(R.id.finishButton);

        name = getIntent().getStringExtra("NAME");
        score = String.valueOf(getIntent().getIntExtra("CORRECT_ANSWERS", 0));

        congratulationsTextView.setText("Congratulations " + name + "!");
        scoreTextView.setText(score + "/5");

        takeNewQuizButton.setOnClickListener(this);
        finishQuizButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        if (clickedButton.getId() == R.id.takeNewQuizButton) {
            Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
            intent.putExtra("NAME", name);
            startActivity(intent);
            FinishQuizActivity.this.finish();
        } else {
            FinishQuizActivity.this.finish();
            System.exit(0);
        }
    }
}