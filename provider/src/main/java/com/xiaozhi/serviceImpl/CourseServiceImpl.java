package com.xiaozhi.serviceImpl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.xiaozhi.dao.mysql.CourseMySqlDao;
import com.xiaozhi.dao.mysql.StudentCourseDao;
import com.xiaozhi.dao.redis.CourseRedisDao;
import com.xiaozhi.model.CourseVo;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.model.mysql.CourseDo;
import com.xiaozhi.model.mysql.StudentCourseDo;
import com.xiaozhi.result.ResultCode;
import com.xiaozhi.result.resultImpl.ServiceResult;
import com.xiaozhi.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 小智 on 2017/4/27 0027.
 */
@Slf4j
@Service("courseServiceImpl")
public class CourseServiceImpl implements CourseService{
    @Resource
    private CourseRedisDao courseRedisDao;
    @Resource
    private CourseMySqlDao courseMySqlDao;
    @Resource
    private StudentCourseDao studentCourseDao;

    @Override
    public ServiceResult<List<CourseVo>> findList(StudentVo studentVo){
        if (studentVo.isIllegal()) {
            log.warn("参数studentVo不合法，studentVo:{}", studentVo);
            return new ServiceResult<>(ResultCode.PARAMETER_ILLEGAL);
        }
        List<CourseDo> courseDos = courseRedisDao.findAll();
        List<CourseVo> courseVos = Lists.newArrayList();
        courseDos.forEach(courseDo -> {
            //一般会转换数据库的值为显示的值,在此没有进行，而是直接保存
            int remain = courseRedisDao.findCourseNumber(courseDo.getId());
            //解决高并发数据为负为题，只是显示所以不影响数据正确性
            if (remain < 0)
                remain = 0;
            CourseVo courseVo = new CourseVo();
            BeanUtils.copyProperties(courseDo, courseVo);
            courseVo.setRemain(remain);
            courseVo.setSelected(courseVo.getTotal() - remain);
            courseVos.add(courseVo);
        });
        return new ServiceResult<>(courseVos);
    }

    @Override
    public ServiceResult<Void> update(StudentVo studentVo, String courseId, boolean isAdd){
        if (studentVo.isIllegal() && Strings.isNullOrEmpty(courseId)) {
            log.warn("参数studentVo不合法，studentVo:{},courseId:{}", studentVo, courseId);
            return new ServiceResult<>(ResultCode.PARAMETER_ILLEGAL);
        }
        //是否到选课时间   -1：未开始；  0：开始；    1：已结束
        int isSelectedTime = courseRedisDao.isSelectedTime();
        if (isSelectedTime == -1) {
            log.warn("选课时间未到!");
            return new ServiceResult<>(ResultCode.COURSE_SELECTED_TIME);
        }
        if (isSelectedTime == 1) {
            log.warn("选课已结束!");
            return new ServiceResult<>(ResultCode.COURSE_SELECTED_OVER);
        }
        boolean isSucceed = courseRedisDao.update(courseId, isAdd);
        if (isSucceed) {
            //学生表存储 mysql
            StudentCourseDo studentCourseDo = new StudentCourseDo();
            studentCourseDo.setStudentId(studentVo.getId());
            studentCourseDo.setCourseId(courseId);
            studentCourseDao.save(studentCourseDo);
            return new ServiceResult<>();
        } else {
            return new ServiceResult<>(ResultCode.COURSE_HAD_NONE);
        }
    }


    @Override
    public ServiceResult<List<CourseVo>> listStudentSelected(StudentVo studentVo){
        if (studentVo.isIllegal()) {
            log.warn("参数studentVo不合法，studentVo:{}", studentVo);
            return new ServiceResult<>(ResultCode.PARAMETER_ILLEGAL);
        }
        List<CourseVo> courseVos = Lists.newArrayList();
        List<StudentCourseDo> studentCourseDos = studentCourseDao.listByStudentId(studentVo.getId());
        if (CollectionUtils.isEmpty(studentCourseDos)) {
            log.info("该学生还没有选课,studentVo:{}", studentVo);
            return new ServiceResult<>(courseVos);
        }
        studentCourseDos.forEach(studentCourseDo -> {
            CourseDo courseDo = courseMySqlDao.getById(studentCourseDo.getCourseId());
            CourseVo courseVo = new CourseVo();
            BeanUtils.copyProperties(courseDo, courseVo);
            courseVos.add(courseVo);
        });
        return new ServiceResult<>(courseVos);
    }

    @Override
    public ServiceResult<Void> addCourse(StudentVo studentVo, List<CourseVo> courseVos){
        for (CourseVo courseVo : courseVos) {
            CourseDo courseDo = new CourseDo();
            BeanUtils.copyProperties(courseVo, courseDo);
            courseDo.setSelected(0);
            courseDo.setRemain(courseDo.getTotal());
            courseMySqlDao.save(courseDo);   //Mysql保存
            courseRedisDao.save(courseDo);   //Redis保存
        }
        return new ServiceResult<>();
    }

    @Override
    public ServiceResult<Void> setCourseTime(StudentVo studentVo, String startTime, String endTime){
        courseRedisDao.addTime(startTime, endTime);
        //设置选课结束时间同步数据到MYsql
        redisToMySql(endTime, studentVo);
        return new ServiceResult<>();
    }

    @Override
    public ServiceResult<List<CourseVo>> listManageCourse(StudentVo studentVo){
        List<CourseDo> courseDos = courseMySqlDao.listAll();
        List<CourseVo> courseVos = Lists.newArrayList();
        courseDos.forEach(courseDo -> {
            CourseVo courseVo = new CourseVo();
            BeanUtils.copyProperties(courseDo, courseVo);
            courseVos.add(courseVo);
        });
        return new ServiceResult<>(courseVos);
    }

    private void redisToMySql(String time, StudentVo studentVo){
        Date date = new Date(new Long(time));
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(studentVo), date);
    }

    private class MyTimerTask extends TimerTask{
        private StudentVo studentVo;

        public MyTimerTask(StudentVo studentVo){
            this.studentVo = studentVo;
        }

        @Override
        public void run(){
            List<CourseVo> courseVos = findList(studentVo).getData();
            List<CourseDo> courseDos = Lists.newArrayList();
            courseVos.forEach(courseVo -> {
                CourseDo courseDo = new CourseDo();
                BeanUtils.copyProperties(courseVo, courseDo);
                courseDos.add(courseDo);
            });
            courseMySqlDao.updateBatch(courseDos);
        }
    }
}
