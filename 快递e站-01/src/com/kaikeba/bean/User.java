package com.kaikeba.bean;

public class User {
    private String username;
    private String userPhone;
    private String userId;
    private String userPassword;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public User(String username, String userPhone, String userId, String userPassword) {
        this.username = username;
        this.userPhone = userPhone;
        this.userId = userId;
        this.userPassword = userPassword;
    }
}
