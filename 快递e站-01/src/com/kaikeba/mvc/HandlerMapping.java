package com.kaikeba.mvc;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;

public class HandlerMapping {
    private static HashMap<String, MVCMapping> data = new HashMap<>();

    public static void load(InputStream is) {
        Properties ppt = new Properties();
        try {
            ppt.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collection<Object> values = ppt.values();
        for (Object value : values) {
            String clazzName = (String) value;
            try {
                Class<?> clazz = Class.forName(clazzName);
                Object obj = clazz.getConstructor().newInstance();
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    Annotation[] as = method.getAnnotations();
                    if (as != null) {

                        for (Annotation a : as) {
                            if (a instanceof ResponseBody) {
                                MVCMapping mapping = new MVCMapping(obj, method, ResponseType.TEXT);
                                if (!data.containsValue(mapping)) {
                                    data.put(((ResponseBody) a).value(), mapping);
                                } else {
                                    throw new RuntimeException("请求地址重复:" + ((ResponseBody) a).value());
                                }
                            } else if (a instanceof ResponseView) {
                                MVCMapping mapping = new MVCMapping(obj, method, ResponseType.VIEW);
                                if (!data.containsValue(mapping)) {
                                    data.put(((ResponseView) a).value(), mapping);
                                } else {
                                    throw new RuntimeException("请求地址重复:" + ((ResponseView) a).value());
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    //获取集合中指定网址的MVCMapping
    public static MVCMapping get(String uri) {
        return data.get(uri);
    }

    public static class MVCMapping {
        private Object object;
        private Method method;
        private ResponseType type;

        public MVCMapping() {
        }

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public ResponseType getType() {
            return type;
        }

        public void setType(ResponseType type) {
            this.type = type;
        }

        public MVCMapping(Object object, Method method, ResponseType type) {
            this.object = object;
            this.method = method;
            this.type = type;
        }
    }
}
