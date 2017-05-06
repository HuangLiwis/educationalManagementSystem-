package com.xiaozhi.service;

import com.xiaozhi.model.TestVo;
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
public class TestServiceTest{
    @Resource
    private TestService testService;

    @Test
    public void list(){
        TestVo testVo = testService.getTestService("asdas");
        System.out.println(testVo);
    }
}
