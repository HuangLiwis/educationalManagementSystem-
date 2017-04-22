package com.xiaozhi.utils;

import com.google.gson.Gson;


/**
 * Created by Administrator on 2017/4/14 0014.
 * json转换工具类
 */

public class JsonUtils {
    private static Gson gson=new Gson();

    /**
     * 对象转换为json
     */
    public static String object2json(Object object) {
        return gson.toJson(object);
    }

    /**
     * json转换为对象
     */
    public static <T> T json2object(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
