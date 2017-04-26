package com.xiaozhi.controller;

import com.xiaozhi.model.FinancialVo;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.result.resultImpl.ServiceResult;
import com.xiaozhi.result.resultImpl.WebResult;
import com.xiaozhi.service.FinancialService;
import com.xiaozhi.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 小智 on 2017/4/22 0022.
 */
@Slf4j
@Controller
@RequestMapping("financial")
public class FinancialController{
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Resource
    FinancialService financialService;

    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public FinancialVo studentLogin(@CookieValue String student){
        StudentVo studentVo = JsonUtils.json2object(student, StudentVo.class);
        ServiceResult<FinancialVo> result = financialService.findFinancial(studentVo);
        FinancialVo financialVo = null;
        if (result.isSuccess())
            financialVo = result.getData();
        return financialVo;
    }

    @ResponseBody
    @RequestMapping(value = "/addMoneyRecord", method = RequestMethod.POST)
    public WebResult studentLogin(@CookieValue String student,
                                  @RequestBody FinancialVo.MoneyRecord moneyRecord){
        moneyRecord.setTime(dateFormat.format(new Date()));
        StudentVo studentVo = JsonUtils.json2object(student, StudentVo.class);
        ServiceResult<Void> result = financialService.addMoneyRecord(studentVo, moneyRecord);
        if (!result.isSuccess())
            log.warn("摊上大事了");
        return new WebResult<>(moneyRecord);
    }
}
