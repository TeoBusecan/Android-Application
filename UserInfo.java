package com.example.myapplication.model;

public class UserInfo {
    private int id;
    private String email;
    private String password;

    public UserInfo(){}
    public UserInfo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserInfo(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
