package com.xiaozhi.dao;

import com.xiaozhi.dao.mongo.CampusNetDao;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.model.mongo.CampusNetDo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 小智 on 2017/4/18 0018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CampusNetDaoTest{
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Resource
    CampusNetDao campusNetDao;

    // LibraryServiceImpl libraryService = new LibraryServiceImpl();

    private CampusNetDo campusNetDo;

    @Before
    public void initObject(){
        campusNetDo = new CampusNetDo("1305120309", 1, 5.0, 1, 1, 1, 1);
    }

    @Test
    public void save(){
        campusNetDao.save(campusNetDo);
    }

    @Test
    public void update(){
    }

    @Test
    public void find(){
    }
}
