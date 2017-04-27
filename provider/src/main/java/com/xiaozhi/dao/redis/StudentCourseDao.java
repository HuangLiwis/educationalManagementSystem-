package com.xiaozhi.dao.redis;

import com.xiaozhi.model.StudentVo;

/**
 * Created by 小智 on 2017/4/27 0027.
 */
public interface StudentCourseDao{
    void save(StudentVo studentVo, String courseId);

    void update(StudentVo studentVo, String courseId);
}
