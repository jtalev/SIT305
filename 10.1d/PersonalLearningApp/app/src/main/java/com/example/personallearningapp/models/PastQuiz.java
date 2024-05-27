package com.example.personallearningapp.models;

public class PastQuiz {

    private String question;

    private String Answer1;

    private String Answer2;

    private String Answer3;

    private String userAnswer;

    private String actualAnswer;

    public PastQuiz(String question, String answer1, String answer2, String answer3, String userAnswer, String actualAnswer) {
        this.question = question;
        Answer1 = answer1;
        Answer2 = answer2;
        Answer3 = answer3;
        this.userAnswer = userAnswer;
        this.actualAnswer = actualAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return Answer1;
    }

    public void setAnswer1(String answer1) {
        Answer1 = answer1;
    }

    public String getAnswer2() {
        return Answer2;
    }

    public void setAnswer2(String answer2) {
        Answer2 = answer2;
    }

    public String getAnswer3() {
        return Answer3;
    }

    public void setAnswer3(String answer3) {
        Answer3 = answer3;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getActualAnswer() {
        return actualAnswer;
    }

    public void setActualAnswer(String actualAnswer) {
        this.actualAnswer = actualAnswer;
    }
}
