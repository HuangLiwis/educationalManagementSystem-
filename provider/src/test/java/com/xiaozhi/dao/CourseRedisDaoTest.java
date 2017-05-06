package com.xiaozhi.dao;

import com.xiaozhi.dao.redis.CourseRedisDao;
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
public class CourseRedisDaoTest{
    @Resource
    private CourseRedisDao courseRedisDao;

    private CourseDo courseDo;

    @Before
    public void before(){
        courseDo = new CourseDo("01050072", "环境科学导论", "胖子", "14-16", "L1144", "1.5", 60, 0, 60, "理学院");
    }

    @Test
    public void save(){
        courseRedisDao.save(courseDo);
    }

    @Test
    public void update(){
        courseRedisDao.update("01050071", false);
    }

    @Test
    public void list(){
        courseRedisDao.findAll().forEach(System.out::println);
    }

    @Test
    public void deleteAll(){
        courseRedisDao.deleteAll();
    }

    @Test
    public void getNumber(){
        int a = courseRedisDao.findCourseNumber("01050071");
        System.out.println(a);
    }
}
