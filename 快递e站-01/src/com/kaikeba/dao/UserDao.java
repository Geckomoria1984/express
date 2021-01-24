package com.kaikeba.dao;

import com.kaikeba.bean.User;

import java.util.List;

public interface UserDao {
    List<User> findAllUser(boolean limit,int offset,int pageNumber);

    boolean add(User user);

    boolean updateUser(String userPhone,User newUser);

    boolean deleteUser(String userPhone);

    User findByUserPhone(String userPhone);
}
