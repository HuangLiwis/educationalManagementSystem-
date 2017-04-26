package com.xiaozhi.dao.mongo;

import com.xiaozhi.model.mongo.CampusNetDo;
import com.xiaozhi.model.mongo.LibraryInfoDo;

import java.util.List;

/**
 * Created by 小智 on 2017/4/18 0018.
 * 学生基本信息
 */
public interface CampusNetDao{
    void save(CampusNetDo campusNetDo);

    List<CampusNetDo> find(CampusNetDo campusNetDo);

    void update(CampusNetDo campusNetDo);

    void delete(CampusNetDo campusNetDo);
}
