package com.xiaozhi.dao.mysql;

import com.xiaozhi.model.mysql.CourseDo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 小智 on 2017/4/28 0028.
 */
@Repository
public interface CourseMySqlDao{
    void save(@Param("courseDo") CourseDo courseDo);

    /**
     * 获取所有列表
     */
    List<CourseDo> listAll();

    /**
     * 根据ID获取
     */
    CourseDo getById(String courseId);

    /**
     * 批量更新
     */
    void updateBatch(List<CourseDo> courseDos);
}
