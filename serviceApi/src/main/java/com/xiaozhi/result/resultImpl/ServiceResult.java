package com.xiaozhi.result.resultImpl;

import com.xiaozhi.exception.ServiceException;
import com.xiaozhi.result.BaseResult;
import com.xiaozhi.result.ResultCode;

import java.io.Serializable;

/**
 * Created by 小智 on 2017/4/22 0022.
 * <p>
 * Service数据返回封装
 */
public class ServiceResult<T> implements BaseResult, Serializable{
    private int code;               //返回码
    private String describe;        //返回描述
    private T data;                 //返回数据

    public ServiceResult(){
        this.data = null;
        this.code = ResultCode.SUCCESS.getCode();
        this.describe = ResultCode.SUCCESS.getDescribe();
    }

    public ServiceResult(T data){
        this.data = data;
        this.code = ResultCode.SUCCESS.getCode();
        this.describe = ResultCode.SUCCESS.getDescribe();
    }

    public ServiceResult(ServiceException exception){
        this.data = data;
        this.code = exception.getErrorCode();
        this.describe = exception.getErrorDescribe();
    }

    public ServiceResult(ResultCode resultCode){
        this.describe = resultCode.getDescribe();
        this.code = resultCode.getCode();
        this.data = null;
    }

    @Override
    public int getCode(){
        return code;
    }

    @Override
    public String getDescribe(){
        return describe;
    }

    @Override
    public T getData(){
        return data;
    }

    @Override
    public boolean isSuccess(){
        if (this.code == ResultCode.SUCCESS.getCode())
            return true;
        else
            return false;
    }
}
