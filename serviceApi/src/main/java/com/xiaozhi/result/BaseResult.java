package com.xiaozhi.result;

/**
 * Created by 小智 on 2017/4/22 0022.
 * <p>
 * 接口数据返回封装
 */
public interface BaseResult<T>{
    /**
     * 获取返回码
     */
    int getCode();

    /**
     * 获取返回描述
     */
    String getDescribe();

    /**
     * 获取返回数据
     */
     T getData();

    /**
     * 返回是否成功
     */
    boolean isSuccess();
}
