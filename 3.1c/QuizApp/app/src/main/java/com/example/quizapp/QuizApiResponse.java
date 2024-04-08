package com.example.quizapp;

import com.google.gson.annotations.SerializedName;

public class QuizApiResponse {

    @SerializedName("results")
    private QuestionAnswer[] results;

    public QuestionAnswer[] getResults() {
        return results;
    }
}
