package com.kaikeba.controller;

import com.kaikeba.bean.Message;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.AdminService;
import com.kaikeba.util.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class AdminController {
    @ResponseBody("/admin/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean flag = AdminService.login(username, password);
        Message message = null;
        if (flag) {
            message = new Message("登录成功", 0);
            request.getSession().setAttribute("adminUserNmae", username);
            String ip = request.getRemoteAddr();
            Date date = new Date();
            AdminService.updateLoginTimeAndIp(username, date, ip);
            request.getSession().setAttribute("adminUserName", username);
        } else {
            message = new Message("登陆失败", -1);
        }
        String s = JSONUtil.toJSON(message);
        return s;
    }
}
