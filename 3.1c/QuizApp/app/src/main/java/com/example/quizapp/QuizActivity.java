package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private QuizApiService quizService;
    QuestionAnswer[] decodedResults;
    TextView welcomeTextView;
    TextView questionCounterTextView;
    ProgressBar progressBar;
    TextView questionTextView;
    AppCompatButton answerA;
    AppCompatButton answerB;
    AppCompatButton answerC;
    AppCompatButton answerD;
    Button submitButton;
    String selectedAnswer;
    AppCompatButton selectedAnswerButton;
    String name;
    int correctAnswerCounter;
    int questionCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        name = getIntent().getStringExtra("NAME");

        quizService = RetrofitClient.getClient().create(QuizApiService.class);

        fetchQuizData();

        welcomeTextView = findViewById(R.id.welcomeTextView);
        questionCounterTextView = findViewById(R.id.questionCounter);
        progressBar = findViewById(R.id.progressBar);
        questionTextView = findViewById(R.id.questionTextView);
        answerA = findViewById(R.id.answerA);
        answerB = findViewById(R.id.answerB);
        answerC = findViewById(R.id.answerC);
        answerD = findViewById(R.id.answerD);
        submitButton = findViewById(R.id.submitButton);

        welcomeTextView.setText("Welcome " + name + "!");

        answerA.setOnClickListener(this);
        answerB.setOnClickListener(this);
        answerC.setOnClickListener(this);
        answerD.setOnClickListener(this);
        submitButton.setOnClickListener(this);

    }

    private void fetchQuizData() {

        Call<QuizApiResponse> call = quizService.getQuiz(5, 9, "medium", "multiple", "base64");
        call.enqueue(new Callback<QuizApiResponse>() {
            @Override
            public void onResponse(Call<QuizApiResponse> call, Response<QuizApiResponse> response) {
                if (response.isSuccessful()) {
                    QuizApiResponse quizResponse = response.body();
                    if (quizResponse != null) {
                        decodedResults = decodeQuizResponse(quizResponse);
                        questionTextView.setText(decodedResults[0].getQuestion());

                        String[] answers = decodedResults[0].getAllAnswers();
                        answerA.setText(answers[0]);
                        answerB.setText(answers[1]);
                        answerC.setText(answers[2]);
                        answerD.setText(answers[3]);
                    }
                } else {
                    Log.e("MainActivity", "Failed to fetch quiz data: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<QuizApiResponse> call, Throwable throwable) {
                Log.e("MainActivity", "Failed to fetch quiz data: " + throwable.getMessage());
            }
        });
    }

    private QuestionAnswer[] decodeQuizResponse(QuizApiResponse quizResponse) {
        QuestionAnswer[] results = quizResponse.getResults();
        for (QuestionAnswer questionAnswer : results) {
            questionAnswer.setQuestion(decodeBase64(questionAnswer.getQuestion()));
            questionAnswer.setCorrectAnswer(decodeBase64(questionAnswer.getCorrectAnswer()));
            String[] incorrectAnswers = questionAnswer.getIncorrectAnswer();
            for (int i = 0; i < incorrectAnswers.length; i++) {
                incorrectAnswers[i] = decodeBase64(incorrectAnswers[i]);
            }
        }
        return results;
    }

    private String decodeBase64(String encodedString) {
        byte[] decodedByte = Base64.decode(encodedString, Base64.DEFAULT);
        return new String(decodedByte);
    }


    @Override
    public void onClick(View v) {
        answerA.setBackground(getDrawable(R.drawable.button_border));
        answerB.setBackground(getDrawable(R.drawable.button_border));
        answerC.setBackground(getDrawable(R.drawable.button_border));
        answerD.setBackground(getDrawable(R.drawable.button_border));

        Button clickedButton = (Button) v;
        if(clickedButton.getId()==R.id.submitButton){
            String buttonText = clickedButton.getText().toString();
            if(buttonText.equals("Submit")){
                if(selectedAnswerButton == null){
                    int duration = Toast.LENGTH_SHORT;
                    String text = "Select an answer";
                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                    return;
                }
                clickedButton.setText("Next question");
                if (selectedAnswer.equals(decodedResults[questionCounter].getCorrectAnswer())){
                    selectedAnswerButton.setBackground(getDrawable(R.drawable.button_border_correct));
                    correctAnswerCounter++;
                } else {
                    selectedAnswerButton.setBackground(getDrawable(R.drawable.button_border_incorrect));
                }
                questionCounter++;
            } else {
                loadNewQuestion();
                progressBar.setProgress(questionCounter + 1);
                questionCounterTextView.setText((questionCounter + 1) + "/5");
                clickedButton.setText("Submit");
            }
        } else {
            selectedAnswer = clickedButton.getText().toString();
            selectedAnswerButton = findViewById(clickedButton.getId());
            clickedButton.setBackground(getDrawable(R.drawable.button_border_selected));
        }
    }


    void loadNewQuestion() {
        questionTextView.setText(decodedResults[questionCounter].getQuestion());
        String[] answers = decodedResults[questionCounter].getAllAnswers();
        answerA.setText(answers[0]);
        answerB.setText(answers[1]);
        answerC.setText(answers[2]);
        answerD.setText(answers[3]);
    }
}