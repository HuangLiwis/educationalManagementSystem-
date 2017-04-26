package com.xiaozhi.serviceImpl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.xiaozhi.dao.mongo.CampusNetDao;
import com.xiaozhi.model.CampusNetVo;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.model.mongo.CampusNetDo;
import com.xiaozhi.result.ResultCode;
import com.xiaozhi.result.resultImpl.ServiceResult;
import com.xiaozhi.service.CampusNetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 小智 on 2017/4/25 0025.
 */
@Slf4j
@Service("campusNetServiceImpl")
public class CampusNetServiceImpl implements CampusNetService{
    @Resource
    private CampusNetDao campusNetDao;

    @Override
    public ServiceResult<CampusNetVo> findCampusNetVo(StudentVo studentVo){
        if (studentVo.isIllegal()) {
            log.warn("参数studentVo不合法，studentVo:{}", studentVo);
            return new ServiceResult<>(ResultCode.PARAMETER_ILLEGAL);
        }
        CampusNetDo campusNetDo = new CampusNetDo();
        campusNetDo.setId(studentVo.getId());
        List<CampusNetDo> campusNetDos = campusNetDao.find(campusNetDo);
        if (CollectionUtils.isEmpty(campusNetDos)) {
            return new ServiceResult<>(ResultCode.DATABASE_RETURN_NULL);
        }
        CampusNetVo campusNetVo = new CampusNetVo();
        BeanUtils.copyProperties(campusNetDos.get(0), campusNetVo);
        return new ServiceResult<>(campusNetVo);
    }

    @Override
    public ServiceResult<Void> addCampusNetVo(StudentVo studentVo, CampusNetVo campusNetVo){
        return null;
    }

    @Override
    public ServiceResult<Void> updateCampusNetVo(StudentVo studentVo, CampusNetVo campusNetVo){
        if (studentVo.isIllegal() || campusNetVo.getId() == null) {
            log.warn("参数studentVo不合法，studentVo:{},campusNetVo:{}", studentVo, campusNetVo);
            return new ServiceResult<>(ResultCode.PARAMETER_ILLEGAL);
        }
        CampusNetDo campusNetDo = new CampusNetDo();
        BeanUtils.copyProperties(campusNetVo, campusNetDo);
        campusNetDao.update(campusNetDo);
        return new ServiceResult<>();
    }
}
