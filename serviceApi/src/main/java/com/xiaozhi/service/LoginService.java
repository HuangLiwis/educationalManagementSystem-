package com.xiaozhi.service;

import com.xiaozhi.model.StudentVo;

/**
 * Created by 小智 on 2017/4/18 0018.
 * 登录服务
 */
public interface LoginService{
    /**
     * 学生登录
     *
     * @param studentId 学号
     * @param password  密码
     * @return 学生基本信息
     */
    StudentVo studentLogin(String studentId, String password);
}
