package com.xiaozhi.daoImpl.redis;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.xiaozhi.dao.redis.CourseDao;
import com.xiaozhi.model.mysql.CourseDo;
import com.xiaozhi.utils.JsonUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by 小智 on 2017/4/27 0027.
 */
@Component
public class CourseDaoImpl implements CourseDao{
    @Resource
    private Jedis jedis;

    @Override
    public void save(CourseDo courseDo){
        jedis.set(courseDo.getId(), JsonUtils.object2json(courseDo));
        jedis.set(courseDo.getId() + "_number", courseDo.getTotal().toString());
    }

    @Override
    public boolean update(String courseId, boolean isAdd){
        long value;
        if (isAdd) {
            value = jedis.decr(courseId + "_number");
            if (value < 0) {
                //课程已经被选完
                jedis.incr(courseId + "_number");
                return false;
            } else {
                return true;    //选课成功
            }
        } else {
            jedis.incr(courseId + "_number");
            return true;
        }
    }

    @Override
    public List<CourseDo> findAll(){
        Set<String> keys = jedis.keys("*");
        List<CourseDo> courseDos = Lists.newArrayList();
        keys.forEach(key -> {
            String obj = jedis.get(key);
            if (!Strings.isNullOrEmpty(obj)) {
                CourseDo courseDo = JsonUtils.json2object(obj, CourseDo.class);
                courseDos.add(courseDo);
            }
        });
        return courseDos;
    }
}
