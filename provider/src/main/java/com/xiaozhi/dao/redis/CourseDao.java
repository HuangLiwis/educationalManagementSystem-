package com.xiaozhi.dao.redis;

import com.xiaozhi.model.mysql.CourseDo;

import java.util.List;

/**
 * Created by 小智 on 2017/4/27 0027.
 */
public interface CourseDao{
    void save(CourseDo courseDo);

    /**
     * 选课更新
     * @param isAdd    true:选课，dalse：退选
     */
    boolean update(String courseId, boolean isAdd);

    List<CourseDo> findAll();
}
