package com.kaikeba.service;

import com.kaikeba.bean.User;
import com.kaikeba.dao.UserDao;
import com.kaikeba.dao.impl.UserDaoMySql;

import java.util.List;

public   class UserService  {

    private static UserDao dao = new UserDaoMySql();
    public static  List<User> findAllUser(boolean limit,int offset,int pageNumber) {
        return dao.findAllUser(limit, offset, pageNumber);
    }


    public static  boolean add(User user) {
        return dao.add(user);
    }


    public static  boolean updateUser(String userPhone, User newUser) {
        return dao.updateUser(userPhone, newUser);
    }


    public static  boolean deleteUser(String userPhone) {
        return dao.deleteUser(userPhone);
    }


    public static  User findByUserPhone(String userPhone) {
        return dao.findByUserPhone(userPhone);
    }

}
