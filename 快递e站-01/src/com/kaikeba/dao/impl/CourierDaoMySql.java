package com.kaikeba.dao.impl;

import com.google.gson.internal.$Gson$Preconditions;
import com.kaikeba.bean.Courier;
import com.kaikeba.bean.ResultData;
import com.kaikeba.dao.CourierDao;
import com.kaikeba.util.DruidUtil;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.kaikeba.dao.impl.ExpressDaoMySql.SQL_DELETE;

public class CourierDaoMySql implements CourierDao {
    private static final String SQL_FIND_ALL = "select * from courier";
    private static final String SQL_FIND_ALL_LIMIT = "select * from courier limit ?,?";
    private static final String SQL_INSERT = "insert into courier (sysname,sysphone,sysid,syspassword) values(?,?,?,?)";
    private static final String SQL_UPDATE = "update courier set sysname=?,sysid=?,syspassword=? where sysphone=?";
    private static final String SQL_DELETE = "delete from courier where id = ?";
    private static final String SQL_TOTAL = "select count(id) size from courier";
    private static final String SQL_FIND_BY_SYSPHONE = "select * from courier where sysphone=?";

    @Override
    public List<Courier> findAll(boolean limit, int offset, int pageNumber) {
        List<Courier> data = new ArrayList<>();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (limit) {
                ps = conn.prepareStatement(SQL_FIND_ALL_LIMIT);
                ps.setInt(1, offset);
                ps.setInt(2, pageNumber);
            } else {
                ps = conn.prepareStatement(SQL_FIND_ALL);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String sysname = rs.getString("sysname");
                String sysphone = rs.getString("sysphone");
                String sysid = rs.getString("sysid");
                String syspassword = rs.getString("syspassword");
                Courier courier = new Courier(sysname, sysphone, sysid, syspassword);
                data.add(courier);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, ps, rs);
        }
        return data;
    }

    @Override
    public boolean insert(Courier courier) {

        Connection conn = DruidUtil.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, courier.getSysname());
            ps.setString(2, courier.getSysPhone());
            ps.setString(3, courier.getSysid());
            ps.setString(4, courier.getSyspassword());
            return ps.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, ps, null);
        }
        return false;

    }

    @Override
    public boolean update(String sysphone, Courier newC) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(1, newC.getSysname());
            ps.setString(2, newC.getSysid());
            ps.setString(3, newC.getSyspassword());
            ps.setString(4, sysphone);
            return ps.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, ps, null);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_DELETE);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, ps, null);
        }
        return false;
    }


    public int courierTotal() {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int size = 0;
        try {
            ps = connection.prepareStatement(SQL_TOTAL);
            rs = ps.executeQuery();
            if (rs.next()) {
                size = rs.getInt("size");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, ps, rs);
        }
        return size;
    }

    @Override
    public Courier findBySysPhone(String sysphone) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SQL_FIND_BY_SYSPHONE);
            ps.setString(1, sysphone);
            rs = ps.executeQuery();
            if (rs.next()) {
                String sysname = rs.getString("sysname");
                String sysid = rs.getString("sysid");
                String syspassword = rs.getString("syspassword");
                Courier courier = new Courier(sysname, sysphone, sysid, syspassword);
                return courier;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, ps, null);
        }
        return null;

    }


}
