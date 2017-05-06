package com.xiaozhi.daoImpl.redis;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.xiaozhi.dao.redis.CourseRedisDao;
import com.xiaozhi.model.mysql.CourseDo;
import com.xiaozhi.utils.JsonUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by 小智 on 2017/4/27 0027.
 */
@Component
public class CourseRedisDaoImpl implements CourseRedisDao{
    @Resource
    private Jedis jedis;

    @Override
    public void save(CourseDo courseDo){
        jedis.set(courseDo.getId() + "_obj", JsonUtils.object2json(courseDo));
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
        Set<String> keys = jedis.keys("*_obj");
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

    @Override
    public void deleteAll(){
        Set<String> keys = jedis.keys("*");
        keys.forEach(key -> jedis.del(key));
    }

    @Override
    public int findCourseNumber(String courseId){
        return Integer.parseInt(jedis.get(courseId + "_number"));
    }

    @Override
    public void addTime(String startTime, String endTime){
        if (!"NaN".equals(startTime))
            jedis.set("startTime", startTime);
        if (!"NaN".equals(endTime))
            jedis.set("endTime", endTime);
    }

    /**
     * 是否到选课时间   -1：未开始；  0：开始；    1：已结束
     */
    @Override
    public int isSelectedTime(){
        long timeStart = new Long(jedis.get("startTime"));
        long timeEnd = new Long(jedis.get("endTime"));
        long timeNow = new Date().getTime();
        if (timeNow <= timeStart) {
            return -1;
        } else if (timeNow > timeEnd) {
            return 1;
        } else {
            return 0;
        }
    }
}
