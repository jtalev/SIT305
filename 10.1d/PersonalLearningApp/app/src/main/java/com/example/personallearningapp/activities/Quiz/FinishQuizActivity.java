package com.example.personallearningapp.activities.Quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personallearningapp.R;
import com.example.personallearningapp.activities.Profile.ProfileActivity;
import com.example.personallearningapp.activities.Signup.SignupActivity;
import com.example.personallearningapp.activities.Task.TaskActivity;
import com.example.personallearningapp.models.PastQuiz;
import com.example.personallearningapp.models.Question;
import com.example.personallearningapp.models.Quiz;
import com.example.personallearningapp.models.QuizHistory;
import com.example.personallearningapp.models.User;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FinishQuizActivity extends AppCompatActivity {
    TextView q1Text;
    TextView q1Answer;
    TextView q2Text;
    TextView q2Answer;
    TextView q3Text;
    TextView q3Answer;
    AppCompatButton finishButton;
    User user;
    Quiz quiz;
    Question[] questionArr;
    String answer1;
    String answer2;
    String answer3;
    LinearLayout q1Layout;
    LinearLayout q2Layout;
    LinearLayout q3Layout;
    private FinishQuizViewModel viewModel;
    private int correctAnswers;
    private int incorrectAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_quiz);

        viewModel = new FinishQuizViewModel(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            user = getIntent().getSerializableExtra("USER", User.class);
            quiz = getIntent().getSerializableExtra("QUIZ", Quiz.class);
        }
        questionArr = quiz.getQuiz();
        answer1 = getIntent().getStringExtra("ANS_1");
        answer2 = getIntent().getStringExtra("ANS_2");
        answer3 = getIntent().getStringExtra("ANS_3");

        q1Text = findViewById(R.id.q1_text);
        q1Answer = findViewById(R.id.q1_answer);
        q2Text = findViewById(R.id.q2_text);
        q2Answer = findViewById(R.id.q2_answer);
        q3Text = findViewById(R.id.q3_text);
        q3Answer = findViewById(R.id.q3_answer);
        finishButton = findViewById(R.id.finish_quiz_button);
        q1Layout = findViewById(R.id.q1_layout);
        q2Layout = findViewById(R.id.q2_layout);
        q3Layout = findViewById(R.id.q3_layout);

        q1Text.setText(questionArr[0].getQuestion());
        q1Answer.setText(getCorrectAnswer(questionArr[0].getCorrectAnswer(), 0));
        q2Text.setText(questionArr[1].getQuestion());
        q2Answer.setText(getCorrectAnswer(questionArr[1].getCorrectAnswer(), 1));
        q3Text.setText(questionArr[2].getQuestion());
        q3Answer.setText(getCorrectAnswer(questionArr[2].getCorrectAnswer(), 2));

        evaluateUserAnswers();

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start by updating the question counts
                ListenableFuture<Void> updateQuestionCountFuture = viewModel.updateQuestionCount(user.getUid(), 3);
                Futures.addCallback(updateQuestionCountFuture, new FutureCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        // After updating question count, update correct question count
                        ListenableFuture<Void> updateCorrectQuestionCountFuture = viewModel.updateCorrectQuestionCount(user.getUid(), correctAnswers);
                        Futures.addCallback(updateCorrectQuestionCountFuture, new FutureCallback<Void>() {
                            @Override
                            public void onSuccess(Void result) {
                                // After updating correct question count, update incorrect question count
                                ListenableFuture<Void> updateIncorrectQuestionCountFuture = viewModel.updateIncorrectQuestionCount(user.getUid(), incorrectAnswers);
                                Futures.addCallback(updateIncorrectQuestionCountFuture, new FutureCallback<Void>() {
                                    @Override
                                    public void onSuccess(Void result) {
                                        // After all updates, fetch the updated user data
                                        ListenableFuture<User> userFuture = viewModel.getUserById(user.getUid());
                                        Futures.addCallback(userFuture, new FutureCallback<User>() {
                                            @Override
                                            public void onSuccess(User result) {
                                                Intent intent = new Intent(FinishQuizActivity.this, ProfileActivity.class);
                                                intent.putExtra("USER", result);
                                                startActivity(intent);
                                                finish();
                                            }

                                            @Override
                                            public void onFailure(Throwable t) {
                                                Toast.makeText(FinishQuizActivity.this, "Error fetching updated user", Toast.LENGTH_SHORT).show();
                                            }
                                        }, MoreExecutors.directExecutor());
                                    }

                                    @Override
                                    public void onFailure(Throwable t) {
                                        Toast.makeText(FinishQuizActivity.this, "Error updating incorrect question count", Toast.LENGTH_SHORT).show();
                                    }
                                }, MoreExecutors.directExecutor());
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Toast.makeText(FinishQuizActivity.this, "Error updating correct question count", Toast.LENGTH_SHORT).show();
                            }
                        }, MoreExecutors.directExecutor());
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(FinishQuizActivity.this, "Error updating question count", Toast.LENGTH_SHORT).show();
                    }
                }, MoreExecutors.directExecutor());
            }
        });

    }

    private String getCorrectAnswer(String correctAnswerLetter, int questionIndex) {
        String correctAnswer = "";
        if(correctAnswerLetter.equals("A")){
            correctAnswer = questionArr[questionIndex].getOptions()[0];
        } else if (correctAnswerLetter.equals("B")){
            correctAnswer = questionArr[questionIndex].getOptions()[1];
        } else if (correctAnswerLetter.equals("C")){
            correctAnswer = questionArr[questionIndex].getOptions()[2];
        } else if (correctAnswerLetter.equals("D")){
            correctAnswer = questionArr[questionIndex].getOptions()[3];
        }
        return correctAnswer;
    }

    private void evaluateUserAnswers() {
        if (answer1.equals(q1Answer.getText().toString())) {
            q1Layout.setBackground(getDrawable(R.drawable.correct_answer_background));
            correctAnswers += 1;
        } else {
            incorrectAnswers += 1;
        }
        if (answer2.equals(q2Answer.getText().toString())) {
            q2Layout.setBackground(getDrawable(R.drawable.correct_answer_background));
            correctAnswers += 1;
        } else {
            incorrectAnswers += 1;
        }
        if (answer3.equals(q3Answer.getText().toString())) {
            q3Layout.setBackground(getDrawable(R.drawable.correct_answer_background));
            correctAnswers += 1;
        } else {
            incorrectAnswers += 1;
        }
    }
}