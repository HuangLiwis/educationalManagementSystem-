package com.xiaozhi.controller;

import com.xiaozhi.model.CampusNetVo;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.result.resultImpl.ServiceResult;
import com.xiaozhi.result.resultImpl.WebResult;
import com.xiaozhi.service.CampusNetService;
import com.xiaozhi.service.LibraryService;
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
@RequestMapping("campusNetwork")
public class CampusNetController{
    @Resource
    CampusNetService campusNetService;

    @ResponseBody
    @RequestMapping(value = "/networkInfo", method = RequestMethod.GET)
    public CampusNetVo studentLogin(@CookieValue String student){
        StudentVo studentVo = JsonUtils.json2object(student, StudentVo.class);
        ServiceResult<CampusNetVo> result = campusNetService.findCampusNetVo(studentVo);
        CampusNetVo campusNetVo = null;
        if (result.isSuccess())
            campusNetVo = result.getData();
        return campusNetVo;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public WebResult studentLogin(@CookieValue String student,
                                  @RequestBody CampusNetVo campusNetVo){
        StudentVo studentVo = JsonUtils.json2object(student, StudentVo.class);
        ServiceResult<Void> result = campusNetService.updateCampusNetVo(studentVo, campusNetVo);
        if (!result.isSuccess())
            log.warn("摊上大事了");
        return new WebResult();
    }
}
