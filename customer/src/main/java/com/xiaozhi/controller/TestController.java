package com.xiaozhi.controller;

import com.alibaba.dubbo.common.json.JSON;
import com.google.gson.Gson;
import com.xiaozhi.model.TestVo;
import com.xiaozhi.service.TestService;
import com.xiaozhi.utils.JsonUtils;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/4/14 0014.
 */
@Controller
@RequestMapping("mytest")
public class TestController {
    @Resource
    private TestService testService;

    @ResponseBody
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public TestVo mytest() {
        TestVo testVo = testService.getTestService("黄智");
        return testVo;
    }
}
