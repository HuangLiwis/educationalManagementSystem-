package com.xiaozhi.service;

import com.xiaozhi.model.LibraryInfoVo;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.result.BaseResult;

/**
 * Created by 小智 on 2017/4/22 0022.
 * <p>
 * 图书馆服务
 */
public interface LibraryService{
    /**
     * 查询By学号
     */
    BaseResult<LibraryInfoVo> findLibraryInfo(StudentVo studentVo, String studentId);

    /**
     * 增加借阅书籍
     */
    BaseResult<Void> addBorrowBook(StudentVo studentVo, LibraryInfoVo.LibraryBook book);

    /**
     * 更新信息
     */
    BaseResult<Void> updateLibraryInfo(StudentVo studentVo, LibraryInfoVo libraryInfoVo);
}
