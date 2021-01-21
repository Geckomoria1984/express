package com.kaikeba.dao.impl;

import com.google.gson.internal.$Gson$Preconditions;
import com.kaikeba.bean.Express;
import com.kaikeba.bean.Message;
import com.kaikeba.dao.BaseExpressDao;
import com.kaikeba.exception.DuplicateCodeException;
import com.kaikeba.service.ExpressService;
import com.kaikeba.util.DruidUtil;
import com.kaikeba.util.JSONUtil;

import javax.print.attribute.standard.PresentationDirection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressDaoMySql implements BaseExpressDao {
    public static final String SQL_CONSOLE = "SELECT " +
            "COUNT(ID) data1_size," +
            "COUNT(TO_DAYS(INTIME)=TO_DAYS(NOW()) OR NULL) data1_day," +
            "COUNT(STATUS=0 OR NULL) data2_size," +
            "COUNT(TO_DAYS(INTIME)=TO_DAYS(NOW()) AND STATUS=0 OR NULL) data2_day" +
            " FROM EXPRESS";
    //用于查询数据库中的所有快递信息
    public static final String SQL_FIND_ALL = "SELECT * FROM EXPRESS";
    //用于分页查询数据库中的快递信息
    public static final String SQL_FIND_LIMIT = "SELECT * FROM EXPRESS LIMIT ?,?";
    //通过取件码查询快递信息
    public static final String SQL_FIND_BY_CODE = "SELECT * FROM EXPRESS WHERE CODE=?";
    //通过快递单号查询快递信息
    public static final String SQL_FIND_BY_NUMBER = "SELECT * FROM EXPRESS WHERE NUMBER=?";
    //通过录入人手机号查询快递信息
    public static final String SQL_FIND_BY_SYSPHONE = "SELECT * FROM EXPRESS WHERE SYSPHONE=?";
    //通过用户手机号查询用户所有快递
    public static final String SQL_FIND_BY_USERPHONE = "SELECT * FROM EXPRESS WHERE USERPHONE=?";
    //录入快递
    public static final String SQL_INSERT = "INSERT INTO EXPRESS (NUMBER,USERNAME,USERPHONE,COMPANY,CODE,INTIME,STATUS,SYSPHONE) VALUES(?,?,?,?,?,NOW(),0,?)";
    //快递修改
    public static final String SQL_UPDATE = "UPDATE EXPRESS SET NUMBER=?,USERNAME=?,COMPANY=?,STATUS=? WHERE ID=?";
    //快递的状态码改变（取件）
    public static final String SQL_UPDATE_STATUS = "UPDATE EXPRESS SET STATUS=1,OUTTIME=NOW(),CODE=NULL WHERE CODE=?";
    //快递的删除
    public static final String SQL_DELETE = "DELETE FROM EXPRESS WHERE ID=?";

    @Override
    public List<Map<String, Integer>> console() {
        ArrayList<Map<String, Integer>> data = new ArrayList<>();
        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SQL_CONSOLE);
            rs = ps.executeQuery();
            if (rs.next()) {
                int data1_size = rs.getInt("data1_size");
                int data1_day = rs.getInt("data1_day");
                int data2_size = rs.getInt("data2_size");
                int data2_day = rs.getInt("data2_day");
                Map<String, Integer> data1 = new HashMap<>();
                data1.put("data1_size", data1_size);
                data1.put("data1_day", data1_day);
                Map<String, Integer> data2 = new HashMap<>();
                data2.put("data2_size", data2_size);
                data2.put("data2_day", data2_day);
                data.add(data1);
                data.add(data2);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, ps, rs);
        }
        return data;
    }

    @Override
    public List<Express> findAll(boolean limit, int offset, int pageNumber) {
        List<Express> data = new ArrayList<>();
        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (limit) {
                ps = connection.prepareStatement(SQL_FIND_LIMIT);
                ps.setInt(1, offset);
                ps.setInt(2, pageNumber);
            } else {
                ps = connection.prepareStatement(SQL_FIND_ALL);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String number = rs.getString("number");
                String username = rs.getString("username");
                String userPhone = rs.getString("userPhone");
                String company = rs.getString("company");
                String code = rs.getString("code");
                Timestamp intime = rs.getTimestamp("intime");
                Timestamp outtime = rs.getTimestamp("outtime");
                int status = rs.getInt("status");
                String sysPhone = rs.getString("sysPhone");
                Express express = new Express(id, number, username, userPhone, company, code, intime, outtime, status, sysPhone);
                data.add(express);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, ps, rs);
        }
        return data;
    }

    @Override
    public Express findByNumber(String number) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SQL_FIND_BY_NUMBER);
            ps.setString(1, number);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String userPhone = rs.getString("userPhone");
                String company = rs.getString("company");
                String code = rs.getString("code");
                Timestamp intime = rs.getTimestamp("intime");
                Timestamp outtime = rs.getTimestamp("outtime");
                int status = rs.getInt("status");
                String sysPhone = rs.getString("sysPhone");
                Express express = new Express(id, number, username, userPhone, company, code, intime, outtime, status, sysPhone);
                return express;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, ps, rs);
        }
        return null;
    }

    @Override
    public Express findByCode(String code) {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SQL_FIND_BY_CODE);
            ps.setString(1, code);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String number = rs.getString("number");
                String username = rs.getString("username");
                String userPhone = rs.getString("userPhone");
                String company = rs.getString("company");
                Timestamp intime = rs.getTimestamp("intime");
                Timestamp outtime = rs.getTimestamp("outtime");
                int status = rs.getInt("status");
                String sysPhone = rs.getString("sysPhone");
                Express express = new Express(id, number, username, userPhone, company, code, intime, outtime, status, sysPhone);
                return express;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, ps, rs);
        }
        return null;
    }

    @Override
    public List<Express> findByUserPhone(String userPhone) {
        ArrayList<Express> list = new ArrayList<>();
        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SQL_FIND_BY_USERPHONE);
            ps.setString(1, userPhone);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String number = rs.getString("number");
                String username = rs.getString("username");
                String company = rs.getString("company");
                String code = rs.getString("code");
                Timestamp intime = rs.getTimestamp("intime");
                Timestamp outtime = rs.getTimestamp("outtime");
                int status = rs.getInt("status");
                String sysPhone = rs.getString("sysPhone");
                Express express = new Express(id, number, username, userPhone, company, code, intime, outtime, status, sysPhone);
                list.add(express);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, ps, rs);
        }
        return list;
    }

    @Override
    public List<Express> findBySysPhone(String sysPhone) {
        ArrayList<Express> list = new ArrayList<>();
        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SQL_FIND_BY_SYSPHONE);
            ps.setString(1, sysPhone);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String number = rs.getString("number");
                String username = rs.getString("username");
                String userPhone = rs.getString("userPhone");
                String company = rs.getString("company");
                String code = rs.getString("code");
                Timestamp intime = rs.getTimestamp("intime");
                Timestamp outtime = rs.getTimestamp("outtime");
                int status = rs.getInt("status");
                Express express = new Express(id, number, username, userPhone, company, code, intime, outtime, status, sysPhone);
                list.add(express);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, ps, rs);
        }
        return list;
    }

    @Override
    public boolean insert(Express e) throws DuplicateCodeException {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_INSERT);
            ps.setString(1, e.getNumber());
            ps.setString(2, e.getUsername());
            ps.setString(3, e.getUserPhone());
            ps.setString(4, e.getCompany());
            ps.setString(5, e.getCode());
            ps.setString(6, e.getSysPhone());
            return ps.executeUpdate() > 0 ? true : false;
        } catch (SQLException e1) {
            if (e1.getMessage().endsWith("for key 'code'")) {
                DuplicateCodeException e2 = new DuplicateCodeException(e1.getMessage());
                throw e2;
            } else {
                e1.printStackTrace();
            }
        } finally {
            DruidUtil.close(connection, ps, null);
        }
        return false;
    }

    @Override
    public boolean update(int id, Express newExpress) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_UPDATE);
            state.setString(1,newExpress.getNumber());
            state.setString(2,newExpress.getUsername());
            state.setString(3,newExpress.getCompany());
            state.setInt(4,newExpress.getStatus());
            state.setInt(5,id);
            return state.executeUpdate()>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return false;

    }

    @Override
    public boolean updateStatus(String code) {
        //1.    连接的获取
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_UPDATE_STATUS);
            state.setString(1,code);
            return state.executeUpdate()>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        //1.    连接的获取
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_DELETE);
            state.setInt(1,id);
            return state.executeUpdate()>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return false;
    }
}
