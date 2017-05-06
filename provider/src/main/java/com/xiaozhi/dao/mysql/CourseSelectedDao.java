package com.xiaozhi.dao.mysql;

import com.xiaozhi.model.mysql.StudentCourseDo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 小智 on 2017/4/28 0028.
 */
@Repository
public interface CourseSelectedDao{
    /**
     * 增加
     */
    void save(@Param("studentCourseDo") StudentCourseDo studentCourseDo);

    /**
     * 删除
     */
    void delete(@Param("studentCourseDo") StudentCourseDo studentCourseDo);

    /**
     * 查询
     */
    List<StudentCourseDo> listByStudentId(String studentId);
}
