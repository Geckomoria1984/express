package com.kaikeba.util;

import com.google.gson.Gson;
import com.mysql.cj.protocol.x.OkBuilder;

public class JSONUtil {
    private static Gson g = new Gson();

    public static String toJSON(Object object) {
        return g.toJson(object);
    }
}
