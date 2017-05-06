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

    /**
     * 选课列表(从Redis)
     */
    @ResponseBody
    @RequestMapping(value = "/courseList", method = RequestMethod.GET)
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
            if (result.getCode() == ResultCode.COURSE_SELECTED_TIME.getCode())
                return new WebResult(ResultCode.COURSE_SELECTED_TIME);
            if (result.getCode() == ResultCode.COURSE_HAD_NONE.getCode())
                return new WebResult(ResultCode.COURSE_HAD_NONE);
            if (result.getCode() == ResultCode.COURSE_SELECTED_OVER.getCode())
                return new WebResult(ResultCode.COURSE_SELECTED_OVER);
            return new WebResult(ResultCode.FIELD);
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

    /**
     * 学生选课列表
     */
    @ResponseBody
    @RequestMapping(value = "/listStudentCourse", method = RequestMethod.GET)
    public WebResult listStudentCourse(@CookieValue String student){
        StudentVo studentVo = JsonUtils.json2object(student, StudentVo.class);
        ServiceResult<List<CourseVo>> result = courseService.listStudentSelected(studentVo);
        if (!result.isSuccess()) {
            log.warn("摊上大事了");
            return new WebResult(ResultCode.COURSE_HAD_NONE);
        }
        return new WebResult<>(result.getData());
    }

    /**
     * 课程管理---增加课程
     */
    @ResponseBody
    @RequestMapping(value = "/addCourseData", method = RequestMethod.POST)
    public WebResult addCourseData(@CookieValue String student,
                                   @RequestBody List<CourseVo> courseVos){
        StudentVo studentVo = JsonUtils.json2object(student, StudentVo.class);
        ServiceResult<Void> result = courseService.addCourse(studentVo, courseVos);
        if (!result.isSuccess()) {
            log.warn("摊上大事了");
        }
        return new WebResult<>();
    }

    /**
     * 课程管理---增加选课开始结束时间
     */
    @ResponseBody
    @RequestMapping(value = "/addCourseTime", method = RequestMethod.GET)
    public WebResult addCourseTime(@CookieValue String student,
                                   @RequestParam String startTime,
                                   @RequestParam String endTime){
        StudentVo studentVo = JsonUtils.json2object(student, StudentVo.class);
        ServiceResult<Void> result = courseService.setCourseTime(studentVo, startTime, endTime);
        if (!result.isSuccess()) {
            log.warn("摊上大事了");
        }
        return new WebResult<>();
    }

    /**
     * 课程管理列表（从MYSQL）
     */
    @ResponseBody
    @RequestMapping(value = "/listManageCourse", method = RequestMethod.GET)
    public WebResult listManageCourse(@CookieValue String student){
        StudentVo studentVo = JsonUtils.json2object(student, StudentVo.class);
        ServiceResult<List<CourseVo>> result = courseService.listManageCourse(studentVo);
        if (!result.isSuccess()) {
            log.warn("摊上大事了");
        }
        return new WebResult<>(result.getData());
    }
}
