package com.xiaozhi.service;

import com.xiaozhi.model.FinancialVo;
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
public class FinancialServiceTest{
    @Resource
    FinancialService financialService;

    private FinancialVo financialVo;
    private StudentVo studentVo;

    @Before
    public void before(){
        studentVo = new StudentVo();
        studentVo.setId("1305120309");
        financialVo = new FinancialVo();
        financialVo.setId("1305120309");
        financialVo.setMoney(100.0);
    }

    @Test
    public void find(){
        financialVo = financialService.findFinancial(studentVo).getData();
        System.out.println(financialVo);
    }

    @Test
    public void update(){
        FinancialVo.MoneyRecord moneyRecord = new FinancialVo.MoneyRecord();
        moneyRecord.setTime("2017 7 5 20:22:00");
        moneyRecord.setFee(2.5);
        moneyRecord.setType("图书馆欠费");
        financialService.addMoneyRecord(studentVo, moneyRecord);
    }
}
