package com.example.itubeapp.data.user;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    private int uid;

    @ColumnInfo(name = "full_name")
    private String fullName;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    public User() {}
    public User(String _fullName, String _username, String _password) {
        this.fullName = _fullName;
        this.username = _username;
        this.password = _password;
    }

    public int getUid() {return uid;}
    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFullName() {return fullName;}
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {return username;}
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {return password;}
    public void setPassword(String password) {
        this.password = password;
    }
}
