package com.kaikeba.service;

import com.kaikeba.dao.BaseAdminDao;
import com.kaikeba.dao.impl.AdminDaoMySql;

import java.util.Date;

public class AdminService  {
    private static BaseAdminDao dao = new AdminDaoMySql();
    public static boolean login(String username, String password) {
        return dao.login(username, password);
    }


    public static void updateLoginTimeAndIp(String username, Date date, String ip) {
        dao.updateLoginTimeAndIp(username, date, ip);
    }
}
