package com.xiaozhi.result;

/**
 * Created by 小智 on 2017/4/22 0022.
 * <p>
 * 返回数据状态数据
 */
public enum ResultCode{
    SUCCESS(200, "返回成功"),
    PARAMETER_ILLEGAL(201, "参数不合法"),
    NullPointerException(202, "空指针异常"),
    DATABASE_RETURN_NULL(203, "数据库返回为空"),

    COURSE_HAD_NONE(204, "课程已经被选完"),
    COURSE_SELECTED_TIME(205,"选课时间未到"),
    COURSE_SELECTED_OVER(206,"选课已结束"),
    FIELD(999, "Service返回失败");

    private int code;               //返回码
    private String describe;        //返回描述

    ResultCode(int code, String describe){
        this.code = code;
        this.describe = describe;
    }

    /**
     * 根据code返回对象
     */
    public static ResultCode getResultCode(int code){
        for (ResultCode resultCode : ResultCode.values()) {
            if (resultCode.getCode() == code)
                return resultCode;
        }
        return null;
    }

    public int getCode(){
        return code;
    }

    public String getDescribe(){
        return describe;
    }
}
