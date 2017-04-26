package com.xiaozhi.dao;

import com.xiaozhi.dao.mongo.FinancialDao;
import com.xiaozhi.model.mongo.FinancialDo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

/**
 * Created by 小智 on 2017/4/18 0018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FinancialDaoTest{
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Resource
    FinancialDao financialDao;


    private FinancialDo financialDo;

    @Before
    public void initObject(){
        financialDo = new FinancialDo();
        financialDo.setMoney(100.0);
        financialDo.setId("1305120309");
    }

    @Test
    public void save(){
        financialDao.save(financialDo);
    }

    @Test
    public void update(){
    }

    @Test
    public void find(){
    }
}
