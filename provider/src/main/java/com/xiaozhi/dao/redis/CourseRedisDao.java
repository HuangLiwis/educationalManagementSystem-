package com.xiaozhi.dao.redis;

import com.xiaozhi.model.mysql.CourseDo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 小智 on 2017/4/27 0027.
 */
public interface CourseRedisDao{
    void save(CourseDo courseDo);

    /**
     * 选课更新
     *
     * @param isAdd true:选课，dalse：退选
     */
    boolean update(String courseId, boolean isAdd);

    /**
     *是否到选课时间   -1：未开始；  0：开始；    1：已结束
     */
    int isSelectedTime();

    List<CourseDo> findAll();

    void deleteAll();

    int findCourseNumber(String courseId);

    void addTime(String startTime, String endTime);
}
