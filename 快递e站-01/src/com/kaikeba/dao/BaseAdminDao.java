package com.kaikeba.dao;

import java.util.Date;

public interface BaseAdminDao {
    boolean login(String username, String password);

    void updateLoginTimeAndIp(String username, Date date, String ip);
}
