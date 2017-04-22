package com.xiaozhi.exception;

import com.xiaozhi.result.ResultCode;

/**
 * Created by 小智 on 2017/4/22 0022.
 * 自定义异常，用于service出现的各种异常，方便AOP处理
 */
public class ServiceException extends Exception{
    /**
     * @see com.xiaozhi.result.ResultCode
     */
    private int errorCode;               //返回码
    private String errorDescribe;        //返回描述

    public ServiceException(Throwable cause, ResultCode resultCode){
        super(cause);
        this.errorCode = resultCode.getCode();
        this.errorDescribe = resultCode.getDescribe();
    }
    public ServiceException(ResultCode resultCode){
        this.errorCode = resultCode.getCode();
        this.errorDescribe = resultCode.getDescribe();
    }

    public int getErrorCode(){
        return errorCode;
    }

    public String getErrorDescribe(){
        return errorDescribe;
    }
}
