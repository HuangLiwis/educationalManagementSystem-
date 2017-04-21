package com.xiaozhi.serviceImpl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.collect.Lists;
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
public class TestServiceImpl implements TestService {
    @Resource
    TestDao testDao;
    public TestVo getTestService(String name) {
        log.info("customer来源名字为：{}", name);
        TestDo testDo=testDao.findUser(new TestDo()).get(0);
        TestVo testVo=new TestVo();
        BeanUtils.copyProperties(testDo,testVo);
        return testVo;
    }
}
