package com.example.quizapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kotlin.collections.ArrayDeque;

public class QuestionAnswer {

    @SerializedName("question")
    private String question;

    @SerializedName("correct_answer")
    private String correctAnswer;

    @SerializedName("incorrect_answers")
    private String[] incorrectAnswers;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String[] getIncorrectAnswer() {
        return incorrectAnswers;
    }

    public String[] getAllAnswers() {
        List<String> answers = new ArrayList<>();
        Collections.addAll(answers, incorrectAnswers);
        answers.add(correctAnswer);
        Collections.shuffle(answers);
        return answers.toArray(new String[0]);
    }
}
