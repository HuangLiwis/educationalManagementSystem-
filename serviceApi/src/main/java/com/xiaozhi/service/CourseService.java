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
     * 获取数据列表
     */
    ServiceResult<List<CourseVo>> findList(StudentVo studentVo);

    /**
     * 选课数据更新
     * @param isAdd 选课？退选?
     */
    ServiceResult<Void> update(StudentVo studentVo, String courseId,boolean isAdd);


}
