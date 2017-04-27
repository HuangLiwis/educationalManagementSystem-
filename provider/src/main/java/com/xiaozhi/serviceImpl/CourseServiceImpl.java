package com.xiaozhi.serviceImpl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.xiaozhi.dao.redis.CourseDao;
import com.xiaozhi.model.CourseVo;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.model.mysql.CourseDo;
import com.xiaozhi.result.ResultCode;
import com.xiaozhi.result.resultImpl.ServiceResult;
import com.xiaozhi.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 小智 on 2017/4/27 0027.
 */
@Service("courseServiceImpl")
@Slf4j
public class CourseServiceImpl implements CourseService{
    @Resource
    private CourseDao courseDao;

    @Override
    public ServiceResult<List<CourseVo>> findList(StudentVo studentVo){
        if (studentVo.isIllegal()) {
            log.warn("参数studentVo不合法，studentVo:{}", studentVo);
            return new ServiceResult<>(ResultCode.PARAMETER_ILLEGAL);
        }
        List<CourseDo> courseDos = courseDao.findAll();
        List<CourseVo> courseVos = Lists.newArrayList();
        courseDos.forEach(courseDo -> {
            //一般会转换数据库的值为显示的值
            CourseVo courseVo = new CourseVo();
            BeanUtils.copyProperties(courseDo, courseVo);
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
        boolean isSucceed = courseDao.update(courseId, isAdd);
        if (isSucceed) {
            //todo 学生表存储
            return new ServiceResult<>();
        }
        return new ServiceResult<>(ResultCode.FIELD);
    }
}
