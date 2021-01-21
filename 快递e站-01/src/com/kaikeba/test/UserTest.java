package com.kaikeba.test;

import com.kaikeba.bean.Express;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.ExpressService;
import com.kaikeba.util.DruidUtil;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserTest {
    private String SQL = "insert into express (number,username,userphone,company,code,intime,outtime,status,sysphone) values(?,?,?,?,?,?,?,?,?)";
    @ResponseBody("/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("测试成功");
        return "dengluchenggong";
    }

    @Test
    public void insert() {
        Connection connection = DruidUtil.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);
            ps.setString(1, "123455");
            ps.setString(2, "Mike");
            ps.setString(3, "13845617892");
            ps.setString(4, "京东快递");
            ps.setString(5, "1245556");
            ps.setDate(6,new Date(new java.util.Date().getTime()));
            ps.setDate(7,new Date(new java.util.Date().getTime()));
            ps.setInt(8, 1);
            ps.setString(9, "18412364789");
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(connection, ps, null);
        }

    }

    @Test
    public void find() {
        Express byNumber = ExpressService.findByNumber("123455");
        System.out.println(byNumber);
    }

}

