package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Base64;

public class MainActivity extends AppCompatActivity {

    private QuizApiService quizService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quizService = RetrofitClient.getClient().create(QuizApiService.class);

        fetchQuizData();
    }

    private void fetchQuizData() {

        Call<QuizApiResponse> call = quizService.getQuiz(5, 9, "medium", "multiple", "base64");
        call.enqueue(new Callback<QuizApiResponse>() {
            @Override
            public void onResponse(Call<QuizApiResponse> call, Response<QuizApiResponse> response) {
                if (response.isSuccessful()) {
                    QuizApiResponse quizResponse = response.body();
                    if (quizResponse != null) {
                        QuestionAnswer[] decodedResults = decodeQuizResponse(quizResponse);
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
}