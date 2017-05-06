package com.xiaozhi.dao;

import com.xiaozhi.dao.mysql.StudentCourseDao;
import com.xiaozhi.dao.mysql.TestDao;
import com.xiaozhi.model.mysql.StudentCourseDo;
import com.xiaozhi.model.mysql.TestDo;
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
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class StudentCourseMySqlDaoTest{
    @Resource
    StudentCourseDao studentCourseDao;
    @Resource
    TestDao testDao;

    private StudentCourseDo studentCourseDo;


    @Before
    public void initObject(){
        studentCourseDo = new StudentCourseDo("112266", "1332255");
    }

    @Test
    public void save(){
        studentCourseDao.save(studentCourseDo);
    }

    @Test
    public void delete(){
        studentCourseDao.delete(studentCourseDo);
    }

    @Test
    public void list(){
        studentCourseDao.listByStudentId("112233").forEach(System.out::println);
    }

}
