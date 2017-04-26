package com.xiaozhi.serviceImpl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.xiaozhi.dao.mongo.LibraryInfoDao;
import com.xiaozhi.exception.ServiceException;
import com.xiaozhi.model.LibraryInfoVo;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.model.mongo.LibraryInfoDo;
import com.xiaozhi.result.ResultCode;
import com.xiaozhi.result.resultImpl.ServiceResult;
import com.xiaozhi.result.BaseResult;
import com.xiaozhi.service.LibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialException;
import java.util.IllegalFormatException;
import java.util.List;

/**
 * Created by 小智 on 2017/4/22 0022.
 */
@Slf4j
@Service("libraryServiceImpl")
public class LibraryServiceImpl implements LibraryService{
    @Resource
    LibraryInfoDao libraryInfoDao;

    @Override
    public ServiceResult<LibraryInfoVo> findLibraryInfo(StudentVo studentVo){
        if (studentVo.isIllegal()) {
            throw new ServiceException(ResultCode.PARAMETER_ILLEGAL);
        }
        LibraryInfoVo libraryInfoVo = new LibraryInfoVo();
        LibraryInfoDo libraryInfoDo = new LibraryInfoDo();
        libraryInfoDo.setId(studentVo.getId());
        List<LibraryInfoDo> libraryInfoDos = libraryInfoDao.find(libraryInfoDo);
        if (CollectionUtils.isEmpty(libraryInfoDos)) {
            throw new ServiceException(ResultCode.DATABASE_RETURN_NULL);
        }
        libraryInfoDo = libraryInfoDos.get(0);
        BeanUtils.copyProperties(libraryInfoDo, libraryInfoVo);
        return new ServiceResult<>(libraryInfoVo);
    }

    @Override
    public ServiceResult<Void> addBorrowBook(StudentVo studentVo, LibraryInfoVo.LibraryBook book){
        if (studentVo.isIllegal()) {
            throw new ServiceException(ResultCode.PARAMETER_ILLEGAL);
        }
        LibraryInfoDo libraryInfoDo = new LibraryInfoDo();
        libraryInfoDo.setId(studentVo.getId());
        LibraryInfoDo oldLibraryInfoDo = libraryInfoDao.find(libraryInfoDo).get(0);
        libraryInfoDo.setBorrows(oldLibraryInfoDo.getBorrows() + 1);
        LibraryInfoDo.LibraryBook bookDo = new LibraryInfoDo.LibraryBook();
        BeanUtils.copyProperties(book, bookDo);
        libraryInfoDo.setLibraryBook(bookDo);
        libraryInfoDao.update(libraryInfoDo);
        return new ServiceResult<>();
    }

    @Override
    public ServiceResult<Void> updateLibraryInfo(StudentVo studentVo, LibraryInfoVo libraryInfoVo){
        if (studentVo.isIllegal()) {
            throw new ServiceException(ResultCode.PARAMETER_ILLEGAL);
        }
        LibraryInfoDo libraryInfoDo = new LibraryInfoDo();
        BeanUtils.copyProperties(libraryInfoVo, libraryInfoDo);
        //BeanUtils.copyProperties(libraryInfoVo.getLibraryBook(), libraryInfoDo.getLibraryBook());
        //LibraryInfoDo oldLibraryInfoDo = libraryInfoDao.find(libraryInfoDo).get(0);
        //if (libraryInfoDo.getLibraryBook().getBookId() != null)
        //   libraryInfoDo.setBorrows(oldLibraryInfoDo.getBorrows() + 1);
        libraryInfoDao.update(libraryInfoDo);
        return new ServiceResult<>();
    }
}
