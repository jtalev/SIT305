package com.example.personallearningapp.models;

import java.util.List;

public class QuizHistory {

    private List<PastQuiz> history;

    public QuizHistory(List<PastQuiz> history) {
        this.history = history;
    }

    public List<PastQuiz> getHistory() {
        return history;
    }

    public void setHistory(List<PastQuiz> history) {
        this.history = history;
    }
}
