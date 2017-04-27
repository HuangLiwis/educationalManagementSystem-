package com.xiaozhi.controller;

import com.xiaozhi.model.CourseVo;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.result.ResultCode;
import com.xiaozhi.result.resultImpl.ServiceResult;
import com.xiaozhi.result.resultImpl.WebResult;
import com.xiaozhi.service.CourseService;
import com.xiaozhi.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 小智 on 2017/4/27 0027.
 */
@Controller
@RequestMapping("beansOffice")
@Slf4j
public class BeansOfficeController{
    @Resource
    private CourseService courseService;

    @ResponseBody
    @RequestMapping(value = "/courseList", method = RequestMethod.POST)
    public WebResult findList(@CookieValue String student){
        StudentVo studentVo = JsonUtils.json2object(student, StudentVo.class);
        ServiceResult<List<CourseVo>> result = courseService.findList(studentVo);
        if (!result.isSuccess()) {
            log.warn("摊上大事了");
            return new WebResult(ResultCode.FIELD);
        }
        return new WebResult<>(result.getData());
    }

    @ResponseBody
    @RequestMapping(value = "/selectCourse", method = RequestMethod.GET)
    public WebResult selectCourse(@CookieValue String student,
                                  @RequestParam String courseId){
        StudentVo studentVo = JsonUtils.json2object(student, StudentVo.class);
        ServiceResult<Void> result = courseService.update(studentVo, courseId, true);
        if (!result.isSuccess()) {
            log.warn("摊上大事了");
            return new WebResult(ResultCode.COURSE_HAD_NONE);
        }
        return new WebResult<>();
    }

    @ResponseBody
    @RequestMapping(value = "/cancelCourse", method = RequestMethod.GET)
    public WebResult cancelCourse(@CookieValue String student,
                                  @RequestParam String courseId){
        StudentVo studentVo = JsonUtils.json2object(student, StudentVo.class);
        ServiceResult<Void> result = courseService.update(studentVo, courseId, false);
        if (!result.isSuccess()) {
            log.warn("摊上大事了");
            return new WebResult(ResultCode.COURSE_HAD_NONE);
        }
        return new WebResult<>();
    }
}
