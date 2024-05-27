package com.example.personallearningapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "user_interest",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "username",
                childColumns = "username",
                onDelete = ForeignKey.CASCADE))
public class UserInterest {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "interest")
    private String interest;

    public UserInterest() {
    }

    public UserInterest(String username, String interest) {
        this.username = username;
        this.interest = interest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
