package com.xiaozhi.dao.mongo;

import com.xiaozhi.model.mongo.LibraryInfoDo;
import com.xiaozhi.model.mongo.StudentDo;

import java.util.List;

/**
 * Created by 小智 on 2017/4/18 0018.
 * 学生基本信息
 */
public interface LibraryInfoDao{
    void save(LibraryInfoDo libraryInfoDo);

    List<LibraryInfoDo> find(LibraryInfoDo libraryInfoDo);

    void update(LibraryInfoDo libraryInfoDo);

    void delete(LibraryInfoDo libraryInfoDo);
}
