package com.xiaozhi.daoImpl.redis;

import com.xiaozhi.dao.mongo.StudentDao;
import com.xiaozhi.dao.redis.StudentCourseDao;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.model.mongo.StudentDo;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 小智 on 2017/4/27 0027.
 */
@Component
public class StudentCourseDaoImpl implements StudentCourseDao{
    @Resource
    private Jedis jedis;

    @Override
    public void save(StudentVo studentVo, String courseId){
        jedis.set(studentVo.getId(), courseId);
    }

    @Override
    public void update(StudentVo studentVo, String courseId){
        //todo 使用列表存储
    }
}
