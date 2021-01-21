package com.kaikeba.service;

import com.kaikeba.bean.Express;
import com.kaikeba.dao.BaseExpressDao;

import com.kaikeba.dao.impl.ExpressDaoMySql;
import com.kaikeba.exception.DuplicateCodeException;
import com.kaikeba.util.RandomUtil;
import com.kaikeba.util.SMSUtil;

import java.util.List;
import java.util.Map;

  public  class ExpressService {
    private static BaseExpressDao dao = new ExpressDaoMySql();


     public static  List<Map<String, Integer>> console() {
        return dao.console();
    }


     public static  List<Express> findAll(boolean limit, int offset, int pageNumber) {
        return dao.findAll(limit,offset,pageNumber);
    }


     public static  Express findByNumber(String number) {
         return dao.findByNumber(number);
    }


     public static  Express findByCode(String code) {
        return dao.findByCode(code);
    }


     public static  List<Express> findByUserPhone(String userPhone) {
         return dao.findByUserPhone(userPhone);
    }


     public static  List<Express> findBySysPhone(String sysPhone) {
         return dao.findBySysPhone(sysPhone);
    }


     public static  boolean insert(Express e) throws DuplicateCodeException {
         return dao.insert(e);
    }


     public static  boolean update(int id, Express newExpress) {
         return dao.update(id, newExpress);
    }


     public static  boolean updateStatus(String code) {
         return dao.updateStatus(code);
    }


     public static  boolean delete(int id) {
         return dao.delete(id);
    }
}

