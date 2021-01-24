package com.kaikeba.controller;

import com.kaikeba.bean.Courier;
import com.kaikeba.bean.Message;
import com.kaikeba.bean.ResultData;
import com.kaikeba.dao.CourierDao;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.CourierService;
import com.kaikeba.util.JSONUtil;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CourierController {
     @ResponseBody("/courier/findall.do")
     public String list(HttpServletRequest request, HttpServletResponse response) {
          Integer offset = Integer.parseInt(request.getParameter("offset"));
          int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
          List<Courier> courierList = CourierService.findAll(true, offset, pageNumber);
          int total = CourierService.courierTotal();
          ResultData<Courier> rows = new ResultData<>();
          if (courierList != null) {
               rows.setRows(courierList);
               rows.setTotal(total);
          }
          String json = JSONUtil.toJSON(rows);
          return json;
     }

     @ResponseBody("/courier/add.do")
     public String add(HttpServletRequest request, HttpServletResponse response) {
          String sysname = request.getParameter("sysname");
          String sysPhone = request.getParameter("sysPhone");
          String sysid = request.getParameter("sysid");
          String syspassword = request.getParameter("syspassword");
          Courier courier = new Courier(sysname, sysPhone, sysid, syspassword);
          Message message = new Message();
          boolean flag = CourierService.insert(courier);
          if (flag) {
               message.setStatus(0);
               message.setResult("增加成功");
          } else {
               message.setStatus(-1);
          }
          String json = JSONUtil.toJSON(message);
          return json;
     }

     @ResponseBody("/courier/update.do")
     public String update(HttpServletRequest request, HttpServletResponse response) {
          String sysphone = request.getParameter("sysphone");
          Courier courier = CourierService.findBySysPhone(sysphone);
          Message message = new Message();
          if (courier != null) {
               message.setStatus(0);
               message.setData(courier);
               message.setResult("修改成功");
          } else {
               message.setStatus(-1);
               message.setResult("修改失败");
          }
          String json = JSONUtil.toJSON(message);
          return json;
     }
}
