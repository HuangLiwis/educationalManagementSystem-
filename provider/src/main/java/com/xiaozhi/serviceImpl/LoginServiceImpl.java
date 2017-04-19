package com.xiaozhi.serviceImpl;

import com.xiaozhi.dao.mongo.StudentDao;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.model.mongo.StudentDo;
import com.xiaozhi.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 小智 on 2017/4/18 0018.
 */
@Service("loginServiceImpl")
public class LoginServiceImpl implements LoginService{
    @Resource
    private StudentDao studentDao;

    @Override
    public StudentVo studentLogin(String studentId, String password){
        StudentDo studentDo = new StudentDo();
        studentDo.setId(studentId);
        studentDo.setPassword(password);
        studentDo = studentDao.find(studentDo).get(0);
        StudentVo studentVo = new StudentVo();
        BeanUtils.copyProperties(studentDo, studentVo);
        return studentVo;
    }
}
