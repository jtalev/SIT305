package com.example.personallearningapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.checkerframework.common.aliasing.qual.Unique;

import java.io.Serializable;

@Entity(tableName = "user", indices = {@Index(value = {"username"}, unique = true)})
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    private int uid;

    @ColumnInfo(name = "username")
    @Unique
    private String username;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "question_count")
    private int questionCount;

    @ColumnInfo(name = "correct_question_count")
    private int correctQuestionCount;

    @ColumnInfo(name = "incorrect_question_count")
    private int incorrectQuestionCount;

    @ColumnInfo(name="account_type")
    private int accountType = 0;

    public User() {
    }

    public User(
            String firstName,
            String lastName,
            String username,
            String email,
            String password,
            int questionCount,
            int correctQuestionCount,
            int incorrectQuestionCount) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.questionCount = questionCount;
        this.correctQuestionCount = correctQuestionCount;
        this.incorrectQuestionCount = incorrectQuestionCount;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public int getCorrectQuestionCount() {
        return correctQuestionCount;
    }

    public void setCorrectQuestionCount(int correctQuestionCount) {
        this.correctQuestionCount = correctQuestionCount;
    }

    public int getIncorrectQuestionCount() {
        return incorrectQuestionCount;
    }

    public void setIncorrectQuestionCount(int incorrectQuestionCount) {
        this.incorrectQuestionCount = incorrectQuestionCount;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}
