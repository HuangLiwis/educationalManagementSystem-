package com.xiaozhi.dao;

import com.xiaozhi.dao.redis.CourseDao;
import com.xiaozhi.model.mysql.CourseDo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by 小智 on 2017/4/27 0027.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-redis.xml"})
public class CourseDaoTest{
    @Resource
    private CourseDao courseDao;

    private CourseDo courseDo;

    @Before
    public void before(){
        courseDo = new CourseDo("01050071", "环境科学导论", "张恒", "14-16", "L1144", "1.5", 60, 0, 60, "理学院");
    }

    @Test
    public void save(){
        courseDao.save(courseDo);
    }
}
