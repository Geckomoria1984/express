package com.kaikeba.controller;

import com.kaikeba.bean.Message;
import com.kaikeba.bean.User;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.UserService;
import com.kaikeba.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserController {
    @ResponseBody("/user/list.do")
    public String list(HttpServletRequest request, HttpServletResponse response) {
        Integer offset = Integer.parseInt(request.getParameter("offset"));
        Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        List<User> userlist = UserService.findAllUser(true, offset, pageNumber);
        Message message = null;
        if (userlist != null) {
            message.setStatus(0);
            message.setData("登录成功");
            message.setData(userlist);
        } else {
            message.setStatus(-1);
            message.setResult("登录失败");
        }
        String json = JSONUtil.toJSON(message);
        return json;
    }
     @ResponseBody("/user/add.do")
    public String addUser(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String userPhone = request.getParameter("userPhone");
        String userId = request.getParameter("userId");
        String userPassword = request.getParameter("userPassword");
        User user = new User(username, userPhone, userId, userPassword);
        Message message = null;
        boolean flag = UserService.add(user);
        if (flag) {
            message = new Message(user, null, 0);
        } else {
            message.setStatus(-1);
        }
        String json = JSONUtil.toJSON(message);
        return json;
    }


}
