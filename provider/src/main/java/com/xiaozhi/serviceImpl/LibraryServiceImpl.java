package com.xiaozhi.serviceImpl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.xiaozhi.dao.mongo.LibraryInfoDao;
import com.xiaozhi.model.LibraryInfoVo;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.model.mongo.LibraryInfoDo;
import com.xiaozhi.model.result.ServiceResult;
import com.xiaozhi.result.BaseResult;
import com.xiaozhi.service.LibraryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 小智 on 2017/4/22 0022.
 */
@Service
public class LibraryServiceImpl implements LibraryService{
    @Resource
    LibraryInfoDao libraryInfoDao;

    @Override
    public BaseResult<LibraryInfoVo> findLibraryInfo(StudentVo studentVo, String studentId){
        LibraryInfoVo libraryInfoVo = new LibraryInfoVo();
        LibraryInfoDo libraryInfoDo = new LibraryInfoDo();
        libraryInfoDo.setId(studentId);
        List<LibraryInfoDo> libraryInfoDos = libraryInfoDao.find(libraryInfoDo);
        if (CollectionUtils.isEmpty(libraryInfoDos)) {
            //throw
        }
        libraryInfoDo = libraryInfoDos.get(0);
        BeanUtils.copyProperties(libraryInfoDo, libraryInfoVo);
        return new ServiceResult<>(libraryInfoVo);
    }

    @Override
    public BaseResult<Void> addBorrowBook(StudentVo studentVo, LibraryInfoVo.LibraryBook book){
        LibraryInfoDo libraryInfoDo = new LibraryInfoDo();
        libraryInfoDo.setId(studentVo.getId());
        LibraryInfoDo.LibraryBook bookDo = new LibraryInfoDo.LibraryBook();
        BeanUtils.copyProperties(book, bookDo);
        libraryInfoDo.setLibraryBook(bookDo);
        libraryInfoDao.update(libraryInfoDo);
        return new ServiceResult<>("");
    }

    @Override
    public BaseResult<Void> updateLibraryInfo(StudentVo studentVo, LibraryInfoVo libraryInfoVo){
        LibraryInfoDo libraryInfoDo = new LibraryInfoDo();
        BeanUtils.copyProperties(libraryInfoVo, libraryInfoDo);
        libraryInfoDao.update(libraryInfoDo);
        return new ServiceResult<>("");
    }
}
