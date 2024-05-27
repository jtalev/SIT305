package com.example.personallearningapp.activities.Quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personallearningapp.R;
import com.example.personallearningapp.models.Question;
import com.example.personallearningapp.models.Quiz;
import com.example.personallearningapp.models.User;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private Quiz quiz;
    private User user;
    private Question[] questionArr;
    private String quizTitle;
    private String quizDescription;
    TextView titleText;
    TextView descriptionText;
    TextView questionText;
    AppCompatButton answerA;
    AppCompatButton answerB;
    AppCompatButton answerC;
    AppCompatButton answerD;
    AppCompatButton submitButton;
    AppCompatButton selectedAnswerButton;
    private String selectedAnswer;
    private int questionCounter = 0;
    private List<String> userAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            quiz = getIntent().getSerializableExtra("QUIZ", Quiz.class);
            user = getIntent().getSerializableExtra("USER", User.class);
        }
        questionArr = quiz.getQuiz();
        quizTitle = getIntent().getStringExtra("TITLE");
        quizDescription = getIntent().getStringExtra("DESCRIPTION");

        titleText = findViewById(R.id.quiz_title);
        descriptionText = findViewById(R.id.quiz_description);
        questionText = findViewById(R.id.question_text);
        answerA = findViewById(R.id.answer_a);
        answerB = findViewById(R.id.answer_b);
        answerC = findViewById(R.id.answer_c);
        answerD = findViewById(R.id.answer_d);
        submitButton = findViewById(R.id.submit_button);

        titleText.setText(quizTitle);
        descriptionText.setText(quizDescription);
        questionText.setText(questionArr[questionCounter].getQuestion());
        answerA.setText(questionArr[questionCounter].getOptions()[0]);
        answerB.setText(questionArr[questionCounter].getOptions()[1]);
        answerC.setText(questionArr[questionCounter].getOptions()[2]);
        answerD.setText(questionArr[questionCounter].getOptions()[3]);

        answerA.setOnClickListener(this);
        answerB.setOnClickListener(this);
        answerC.setOnClickListener(this);
        answerD.setOnClickListener(this);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        answerA.setBackground(getDrawable(R.drawable.user_input_background));
        answerB.setBackground(getDrawable(R.drawable.user_input_background));
        answerC.setBackground(getDrawable(R.drawable.user_input_background));
        answerD.setBackground(getDrawable(R.drawable.user_input_background));

        AppCompatButton button = (AppCompatButton) v;
        if (button == submitButton) {
            handleSubmitButton();
        } else {
            handleAnswerSelection(button);
        }
    }

    private void handleAnswerSelection(AppCompatButton button) {
        selectedAnswer = button.getText().toString();
        selectedAnswerButton = findViewById(button.getId());
        setSelectedButtonBackground(button, R.drawable.interest_button_selected);
    }

    private void handleSubmitButton() {
        if (selectedAnswerButton == null) {
            showNoAnswerSelectedToast();
            return;
        }
        if (submitButton.getText().equals("Submit")) {
            userAnswers.add(selectedAnswer);
            evaluateAnswer();
            updateQuestionCounter();
        } else if (submitButton.getText().equals("Next question")) {
            questionText.setText(questionArr[questionCounter].getQuestion());
            answerA.setText(questionArr[questionCounter].getOptions()[0]);
            answerB.setText(questionArr[questionCounter].getOptions()[1]);
            answerC.setText(questionArr[questionCounter].getOptions()[2]);
            answerD.setText(questionArr[questionCounter].getOptions()[3]);
        }
        updateSubmitButtonText();
    }

    private void evaluateAnswer() {
        if(selectedAnswer.equals(questionArr[questionCounter].getCorrectAnswer())) {
            userAnswers.add(selectedAnswer);
        }
    }

    private void updateQuestionCounter() {
        questionCounter++;
    }

    private void updateSubmitButtonText() {
        if (questionCounter == questionArr.length && submitButton.getText().equals("Submit")) {
            selectedAnswerButton.setBackground(getDrawable(R.drawable.interest_button_selected));
            submitButton.setText("Finish quiz");
        } else if (submitButton.getText().equals("Submit")) {
            submitButton.setText("Next question");
            selectedAnswerButton.setBackground(getDrawable(R.drawable.interest_button_selected));
        } else if (submitButton.getText().equals("Next question")) {
            submitButton.setText("Submit");
        } else if (submitButton.getText().equals("Finish quiz")) {
            Intent intent = new Intent(this, FinishQuizActivity.class);
            intent.putExtra("USER", user);
            intent.putExtra("QUIZ", quiz);
            intent.putExtra("ANS_1", userAnswers.get(0));
            intent.putExtra("ANS_2", userAnswers.get(1));
            intent.putExtra("ANS_3", userAnswers.get(2));
            startActivity(intent);
            finish();
        }
    }

    private void setSelectedButtonBackground(AppCompatButton button, int drawableId) {
        button.setBackground(getDrawable(drawableId));
    }

    private void showNoAnswerSelectedToast() {
        Toast.makeText(this, "Select an answer", Toast.LENGTH_SHORT).show();
    }
}