package com.xiaozhi.dao;

import com.google.common.collect.Lists;
import com.xiaozhi.dao.mysql.CourseMySqlDao;
import com.xiaozhi.model.mysql.CourseDo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 小智 on 2017/4/27 0027.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class CourseMySqlDaoTest{
    @Resource
    private CourseMySqlDao courseMySqlDao;

    private CourseDo courseDo;

    @Before
    public void before(){
        courseDo = new CourseDo("01050072", "环境科学导论", "胖子", "14-16", "L1144", "1.5", 60, 2, 58, "理学院");
    }

    @Test
    public void save(){
        courseMySqlDao.save(courseDo);
    }

    @Test
    public void list(){
        courseMySqlDao.listAll().forEach(System.out::println);
    }

    @Test
    public void deleteAll(){
        courseDo = courseMySqlDao.getById("01050072");
        System.out.println(courseDo);
    }

    @Test
    public void updateBatch(){
        List<CourseDo> courseDos = Lists.newArrayList();
        CourseDo courseDo1 = new CourseDo("01050071", "环境科学导论", "胖子", "14-16", "L1144", "1.5", 60, 1, 59,
                "理学院");
        courseDos.add(courseDo);
        courseDos.add(courseDo1);
        courseDos.forEach(System.out::println);
        courseMySqlDao.updateBatch(courseDos);
    }

}
