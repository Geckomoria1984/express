package com.kaikeba.bean;

public class Courier {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private  String sysname;
    private String sysPhone;
    private String sysid;
    private String syspassword;

    @Override
    public String toString() {
        return "Courier{" +
                "sysname='" + sysname + '\'' +
                ", sysPhone='" + sysPhone + '\'' +
                ", sysid=" + sysid +
                ", syspassword='" + syspassword + '\'' +
                '}';
    }

    public String getSysname() {
        return sysname;
    }

    public void setSysname(String sysname) {
        this.sysname = sysname;
    }

    public String getSysPhone() {
        return sysPhone;
    }

    public void setSysPhone(String sysPhone) {
        this.sysPhone = sysPhone;
    }

    public String  getSysid() {
        return sysid;
    }

    public void setSysid(String sysid) {
        this.sysid = sysid;
    }

    public String getSyspassword() {
        return syspassword;
    }

    public void setSyspassword(String syspassword) {
        this.syspassword = syspassword;
    }

    public Courier() {
    }

    public Courier(String sysname, String sysPhone, String sysid, String syspassword) {
        this.sysname = sysname;
        this.sysPhone = sysPhone;
        this.sysid = sysid;
        this.syspassword = syspassword;
    }
}
