package com.xiaozhi.service;

import com.xiaozhi.model.LibraryInfoVo;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.result.BaseResult;
import com.xiaozhi.result.resultImpl.ServiceResult;

/**
 * Created by 小智 on 2017/4/22 0022.
 * <p>
 * 图书馆服务
 */
public interface LibraryService{
    /**
     * 查询By学号
     */
    ServiceResult<LibraryInfoVo> findLibraryInfo(StudentVo studentVo);

    /**
     * 增加借阅书籍
     */
    ServiceResult<Void> addBorrowBook(StudentVo studentVo, LibraryInfoVo.LibraryBook book);

    /**
     * 更新信息
     */
    ServiceResult<Void> updateLibraryInfo(StudentVo studentVo, LibraryInfoVo libraryInfoVo);
}
