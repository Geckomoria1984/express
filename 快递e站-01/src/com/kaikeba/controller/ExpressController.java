package com.kaikeba.controller;

import com.kaikeba.bean.BootStrapTableExpress;
import com.kaikeba.bean.Express;
import com.kaikeba.bean.Message;


import com.kaikeba.exception.DuplicateCodeException;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.ExpressService;

import com.kaikeba.util.*;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ExpressController {

    @ResponseBody("/express/console.do")
    public String console(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Integer>> data = ExpressService.console();
        Message msg = new Message();
        if (data.size() == 0) {
            msg.setStatus(-1);
        } else {
            msg.setStatus(0);
        }
        msg.setData(data);
        String json = JSONUtil.toJSON(msg);
        return json;
    }


    @ResponseBody("/express/list.do")
    public String list(HttpServletRequest request, HttpServletResponse response) {
        int offset = Integer.parseInt(request.getParameter("offset"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        List<Express> expresslist = ExpressService.findAll(true, offset, pageNumber);
        List<BootStrapTableExpress> btelist = new ArrayList<>();
        for (Express express : expresslist) {
            String intime = DateFormatUtil.format(express.getInTime());
            String outtime = express.getOutTime() == null ? "未出库" : DateFormatUtil.format(express.getOutTime());
            String status = express.getStatus() > 0 ? "已取出" : "未取出";
            BootStrapTableExpress bootStrapTableExpress = new BootStrapTableExpress(express.getId(), express.getNumber(), express.getUsername(), express.getUserPhone(),
                    express.getCompany(), express.getCode(), intime, outtime, status, express.getSysPhone());
            btelist.add(bootStrapTableExpress);
            System.out.println(btelist);
        }
        String json = JSONUtil.toJSON(btelist);
        return json;

    }

    @ResponseBody("/express/insert.do")
    public String insert(HttpServletRequest request, HttpServletResponse response) {
        String number = request.getParameter("number");
        String company = request.getParameter("company");
        String username = request.getParameter("username");
        String userPhone = request.getParameter("userPhone");
        Express e = new Express(number, company, username, userPhone, UserUtil.getUserPhone(request.getSession()));
        Message message = null;
        try {
            boolean flag = ExpressService.insert(e);
            if (flag) {
                message = new Message("登陆成功", 0);
            } else {
                message = new Message("登录失败", -1);
            }
        } catch (DuplicateCodeException duplicateCodeException) {
            duplicateCodeException.printStackTrace();
        }
        String json = JSONUtil.toJSON(message);
        return json;
    }

    @ResponseBody("/express/find.do")
    public String find(HttpServletRequest request, HttpServletResponse response) {
        String number = request.getParameter("number");
        Express express = ExpressService.findByNumber(number);
        Message message = new Message();
        if (express != null) {
            message.setData(express);
            message.setStatus(0);
        } else {
            message.setStatus(-1);
        }
        String json = JSONUtil.toJSON(message);
        System.out.println(json);
        return json;
    }
}
