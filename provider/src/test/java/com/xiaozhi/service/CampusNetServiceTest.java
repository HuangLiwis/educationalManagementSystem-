package com.xiaozhi.service;

import com.xiaozhi.model.CampusNetVo;
import com.xiaozhi.model.StudentVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by 小智 on 2017/4/25 0025.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CampusNetServiceTest{
    @Resource
    CampusNetService campusNetService;

    private CampusNetVo campusNetVo;
    private StudentVo studentVo;

    @Before
    public void before(){
        studentVo = new StudentVo();
        studentVo.setId("1305120309");
        campusNetVo = new CampusNetVo("1305120309", 1, 5.0, 1, 1, 1, 1);
    }

    @Test
    public void find(){
        campusNetVo = campusNetService.findCampusNetVo(studentVo).getData();
        System.out.println(campusNetVo);
    }

    @Test
    public void update(){
        campusNetVo.setOverage(6.0);
        campusNetService.updateCampusNetVo(studentVo, campusNetVo);
    }
}
