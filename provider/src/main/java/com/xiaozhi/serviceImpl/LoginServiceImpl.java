package com.xiaozhi.serviceImpl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.xiaozhi.dao.mongo.StudentDao;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.model.mongo.StudentDo;
import com.xiaozhi.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        List<StudentDo> studentDos = studentDao.find(studentDo);
        if (CollectionUtils.isEmpty(studentDos)) {
            //todo 抛出异常
        }
        studentDo = studentDos.get(0);
        StudentVo studentVo = new StudentVo();
        BeanUtils.copyProperties(studentDo, studentVo);
        return studentVo;
    }
}
