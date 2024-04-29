package com.example.personallearningapp.utils;

import com.example.personallearningapp.models.Quiz;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuizService {
    @GET("getQuiz")
    Call<Quiz> getQuiz(@Query("topic") String topic);
}
