package com.xiaozhi.service;

import com.xiaozhi.model.CampusNetVo;
import com.xiaozhi.model.LibraryInfoVo;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.result.resultImpl.ServiceResult;

/**
 * Created by 小智 on 2017/4/22 0022.
 * <p>
 * 图书馆服务
 */
public interface CampusNetService{
    /**
     * 查询By学号
     */
    ServiceResult<CampusNetVo> findCampusNetVo(StudentVo studentVo);

    /**
     * 增加借阅书籍
     */
    ServiceResult<Void> addCampusNetVo(StudentVo studentVo, CampusNetVo campusNetVo);

    /**
     * 更新信息
     */
    ServiceResult<Void> updateCampusNetVo(StudentVo studentVo, CampusNetVo campusNetVo);
}
