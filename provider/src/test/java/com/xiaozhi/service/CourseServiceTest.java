package com.xiaozhi.service;

import com.xiaozhi.model.StudentVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by 小智 on 2017/4/28 0028.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CourseServiceTest{
    @Resource
    private CourseService courseService;

    private StudentVo studentVo;

    @Before
    public void init(){
        studentVo = new StudentVo();
        studentVo.setId("1305120309");
    }

    @Test
    public void list(){
        courseService.findList(studentVo).getData().forEach(System.out::println);
    }
}
