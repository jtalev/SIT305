package com.example.personallearningapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "user_interest",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "uid",
                childColumns = "uid",
                onDelete = ForeignKey.CASCADE))
public class UserInterest {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "uid")
    private int uid;

    @ColumnInfo(name = "interest")
    private String interest;

    public UserInterest() {
    }

    public UserInterest(int id, int uid, String interest) {
        this.id = id;
        this.uid = uid;
        this.interest = interest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
