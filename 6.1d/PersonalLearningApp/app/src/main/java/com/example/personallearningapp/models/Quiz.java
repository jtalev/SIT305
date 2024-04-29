package com.example.personallearningapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Quiz implements Serializable {
    @SerializedName("quiz")
    private Question[] quiz;

    @SerializedName("topic")
    private String topic;

    public Question[] getQuiz() {
        return quiz;
    }

    public void setQuiz(Question[] quiz) {
        this.quiz = quiz;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
