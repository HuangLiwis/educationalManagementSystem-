package com.xiaozhi.dao.mongo;

import com.xiaozhi.model.mongo.StudentDo;

import java.util.List;

/**
 * Created by 小智 on 2017/4/18 0018.
 * 学生基本信息
 */
public interface StudentDao {
    void save(StudentDo studentDo);
    List<StudentDo> find(StudentDo studentDo);
    void update(StudentDo studentDo);
    void delete(StudentDo studentDo);
}
