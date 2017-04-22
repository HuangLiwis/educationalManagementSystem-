package com.xiaozhi.dao;

import com.xiaozhi.dao.mongo.StudentDao;
import com.xiaozhi.model.mongo.StudentDo;
import com.xiaozhi.staticData.AcademyEnum;
import com.xiaozhi.staticData.MajorEnum;
import com.xiaozhi.staticData.StudentCommonEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by 小智 on 2017/4/18 0018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class StudentDaoTest{
    @Resource
    StudentDao studentDao;

    private StudentDo studentDo;

    @Before
    public void initObject(){
        studentDo = new StudentDo("1305120309", "黄智", "huangzhi", StudentCommonEnum.MAN.getCode(),
                StudentCommonEnum.BENKE.getCode(), AcademyEnum.Conputer.getAcademyId(),
                MajorEnum.SoftwareEngineering.getMajorId(), "18086436490", "610423199405012011");
    }

    @Test
    public void save(){
        studentDao.save(studentDo);
    }

    @Test
    public void update(){
        studentDo.setAcademy(AcademyEnum.English.getAcademyId());
        studentDao.update(studentDo);
    }

    @Test
    public void find(){
        studentDo = new StudentDo();
        studentDo.setGrade(MajorEnum.SoftwareEngineering.getMajorId());
        studentDao.find(studentDo).forEach(System.out::println);
    }
}
