package com.xiaozhi.service;

import com.xiaozhi.model.CourseVo;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.result.resultImpl.ServiceResult;

import java.util.List;

/**
 * Created by 小智 on 2017/4/27 0027.
 */
public interface CourseService{
    /**
     * 增加课程
     */
    ServiceResult<Void> addCourse(StudentVo studentVo, List<CourseVo> courseVos);

    /**
     * 设置选课开始结束时间
     */
    ServiceResult<Void> setCourseTime(StudentVo studentVo, String startTime, String endTime);

    /**
     * 获取数据列表(整个选课列表)(Redis获取)
     */
    ServiceResult<List<CourseVo>> findList(StudentVo studentVo);

    /**
     * 选课数据更新
     *
     * @param isAdd 选课？退选?
     */
    ServiceResult<Void> update(StudentVo studentVo, String courseId, boolean isAdd);

    /**
     * 学生已选课程列表
     */
    ServiceResult<List<CourseVo>> listStudentSelected(StudentVo studentVo);

    /**
     * 选课管理列表
     */
    ServiceResult<List<CourseVo>> listManageCourse(StudentVo studentVo);
}
