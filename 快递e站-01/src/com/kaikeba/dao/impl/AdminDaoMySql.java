package com.kaikeba.dao.impl;

import com.kaikeba.dao.BaseAdminDao;
import com.kaikeba.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AdminDaoMySql implements BaseAdminDao {
    private static final String SQL_LOGIN = "select id from eadmin where username=? and password = ?";
    private static final String SQL_UPDATE_LOGIN = "update eadmin set logintime =?,loginip= ? where username=?";
    @Override
    public boolean login(String username, String password) {
        Connection con = DruidUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(SQL_LOGIN);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(con, ps, rs);
        }
        return false;
    }

    @Override
    public void updateLoginTimeAndIp(String username, Date date, String ip) {
        Connection con = DruidUtil.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(SQL_UPDATE_LOGIN);
            ps.setDate(1, new java.sql.Date(date.getTime()));
            ps.setString(2, ip);
            ps.setString(3, username);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(con, ps, null);
        }

    }
}
