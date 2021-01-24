package com.kaikeba.dao;

import com.kaikeba.bean.Courier;

import java.util.List;
import java.util.zip.CheckedOutputStream;

public interface CourierDao {
    List<Courier> findAll(boolean limit, int offset, int pageNumber);

    boolean insert(Courier courier);

    boolean update(String sysphone, Courier newC);

    boolean delete(int id);

    int courierTotal();

    Courier findBySysPhone(String sysphone);


}
