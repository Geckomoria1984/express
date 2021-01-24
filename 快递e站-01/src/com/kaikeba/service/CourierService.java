package com.kaikeba.service;

import com.kaikeba.bean.Courier;
import com.kaikeba.dao.CourierDao;
import com.kaikeba.dao.impl.CourierDaoMySql;

import java.util.List;

 public  class CourierService {
     private static CourierDao dao = new CourierDaoMySql();

     public static List<Courier> findAll(boolean limit, int offset, int pageNumber) {
        return dao.findAll(limit, offset, pageNumber);
     }


     public static boolean insert(Courier courier) {
        return dao.insert(courier);
     }



     public static  boolean update(String sysphone, Courier newC) {
         return dao.update(sysphone, newC);
    }


     public static  boolean delete(int id) {
         return dao.delete(id);
    }

     public static int courierTotal() {
        return dao.courierTotal();
     }

     public static Courier findBySysPhone(String sysphone) {
         Courier cou = dao.findBySysPhone(sysphone);
         return cou;
     }
}
