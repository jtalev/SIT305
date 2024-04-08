package com.example.quizapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuizApiService {
    @GET("api.php")
    Call<QuizApiResponse> getQuiz(
            @Query("amount") int amount,
            @Query("category") int category,
            @Query("difficulty") String difficulty,
            @Query("type") String type,
            @Query("encode") String encode);
}
