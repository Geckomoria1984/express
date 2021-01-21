package com.kaikeba.bean;

public class User {
    private int id;
    private String number;
    private String username;
    private String userPhone;
    private String code;
    private String address;

    public User() {
    }

    public User(int id, String number, String username, String userPhone, String code, String address) {
        this.id = id;
        this.number = number;
        this.username = username;
        this.userPhone = userPhone;
        this.code = code;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
