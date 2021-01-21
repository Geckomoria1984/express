package com.kaikeba.bean;

import java.util.ArrayList;
import java.util.List;

public class ResultData<T> {
    private List<T> rows = new ArrayList<>();
    private int total;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
