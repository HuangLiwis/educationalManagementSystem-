package com.xiaozhi.staticData;

/**
 * Created by 小智 on 2017/4/22 0022.
 */
public enum StudentCommonEnum{

    MAN(1, "男"),
    WOMAN(2, "女"),

    ZHUANKE(11, "专科"),
    BENKE(12, "本科"),
    YANJIUSHENG(12, "研究生"),
    BOSHI(12, "博士");

    private int code;
    private String describe;

    StudentCommonEnum(int code, String describe){
        this.code = code;
        this.describe = describe;
    }

    public int getCode(){
        return code;
    }

    public String getDescribe(){
        return describe;
    }

    public static String getDescribeByCode(int code){
        for (StudentCommonEnum studentCommonEnum : StudentCommonEnum.values()) {
            if (code == studentCommonEnum.code) {
                return studentCommonEnum.describe;
            }
        }
        return null;
    }
}
