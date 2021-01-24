package com.kaikeba.dao.impl;
import com.kaikeba.bean.User;
import com.kaikeba.dao.UserDao;
import com.kaikeba.util.DruidUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoMySql implements UserDao {
    private static final String SQL_FIND_ALL = "select * from user";
    private static final String SQL_FIND_ALL_LIMIT = "select * from user limit ?,?";
    private static final String SQL_ADD = "insert into user(username,userPhone,userId,userPassword) " +
            "value(?,?,?,?)";
    private static final String SQL_UPDATE = "update user set(username=? ,userId=?,userPassword=?" +
            "where userPhone=?";

    private static final String SQL_DELETE = "delete from user where userPhone=?";
    private static final String SQL_FIND_BY_USERPHONE = "select username,userId,userPassword where userPhone=?";
    @Override
    public List<User> findAllUser(boolean limit,int offset,int pageNumber) {
        ArrayList<User> userlist = new ArrayList<>();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (limit) {

                ps = conn.prepareStatement(SQL_FIND_ALL);
            } else {
                ps = conn.prepareStatement(SQL_FIND_ALL_LIMIT);
                ps.setInt(1, offset);
                ps.setInt(2, pageNumber);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String userPhone = rs.getString("userPhone");
                String userId = rs.getString("userId");
                String userPassword = rs.getString("userPassword");
                User user = new User(username, userPhone, userId, userPassword);
                userlist.add(user);
                return userlist;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public boolean add(User user) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_ADD);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getUserPhone());
            ps.setString(4, user.getUserPassword());
            ps.setString(3, user.getUserId());
            return ps.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, ps, null);
        }
        return false;
    }

    @Override
    public boolean updateUser(String userPhone,User newUser) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(1, newUser.getUsername());
            ps.setString(2, newUser.getUserId());
            ps.setString(3, newUser.getUserPassword());
            ps.setString(4, newUser.getUserPhone());
            return ps.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, ps, null);
        }
        return false;
    }

    @Override
    public boolean deleteUser(String userPhone) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setString(1, userPhone);
            return ps.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, ps, null);
        }
        return false;
    }

    @Override
    public User findByUserPhone(String userPhone) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            ps = connection.prepareStatement(SQL_FIND_BY_USERPHONE);
            ps.setString(1, userPhone);
            rs = ps.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String userId = rs.getString("userId");
                String userPassword = rs.getString("userPassword");
                user = new User(username, userPhone, userId, userPassword);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, ps, rs);
        }
        return user;
    }

}
