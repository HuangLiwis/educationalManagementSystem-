package com.xiaozhi.serviceImpl;

import com.xiaozhi.dao.mysql.Test2Dao;
import com.xiaozhi.dao.mysql.TestDao;
import com.xiaozhi.model.TestVo;
import com.xiaozhi.model.mysql.TestDo;
import com.xiaozhi.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/4/14 0014.
 */
@Slf4j
@Service("testServiceImpl")
public class TestServiceImpl implements TestService{
    @Resource
    private TestDao testDao;
    //    @Resource
//    private CourseRe
// disDao courseRedisDao;
//    @Resource
//    private CourseMySqlDao courseMySqlDao;
//    @Resource
//    private StudentCourseDao studentCourseDao;
    @Resource
    private Test2Dao test2Dao;

    public TestVo getTestService(String name){
//        courseMySqlDao.getById("112233");
//        courseRedisDao.findAll();
        //courseSelectedDao.listByStudentId("112233").forEach(System.out::println);
        test2Dao.findUser(new TestDo()).forEach(System.out::println);
        log.info("customer来源名字为：{}", name);
        TestDo testDo = testDao.findUser(new TestDo()).get(0);
        TestVo testVo = new TestVo();
        BeanUtils.copyProperties(testDo, testVo);
        return testVo;
    }
}
