package com.kaikeba.bean;

public class Message {
    private Object data;
    private String result;
    private int status;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



    public String getResult() {
        return result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Message(Object data, String result, int status) {
        this.data = data;
        this.result =result;
        this.status = status;
    }

    public Message(String result, int status) {
        this.result = result;
        this.status = status;
    }

    public void setResult(String s) {
    }

    public Message() {
    }
}
