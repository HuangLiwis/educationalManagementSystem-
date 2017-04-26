package com.xiaozhi.controller;

import com.xiaozhi.model.LibraryInfoVo;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.result.BaseResult;
import com.xiaozhi.result.ResultCode;
import com.xiaozhi.result.resultImpl.ServiceResult;
import com.xiaozhi.result.resultImpl.WebResult;
import com.xiaozhi.service.LibraryService;
import com.xiaozhi.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 小智 on 2017/4/22 0022.
 */
@Slf4j
@Controller
@RequestMapping("library")
public class LibraryController{
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Resource
    LibraryService libraryService;

    @ResponseBody
    @RequestMapping(value = "/libraryInfo", method = RequestMethod.GET)
    public LibraryInfoVo libraryInfo(@CookieValue String student){
        StudentVo studentVo = JsonUtils.json2object(student, StudentVo.class);
        ServiceResult<LibraryInfoVo> result = libraryService.findLibraryInfo(studentVo);
        LibraryInfoVo libraryInfoVo = null;
        if (result.isSuccess())
            libraryInfoVo = result.getData();
        return libraryInfoVo;
    }

    @RequestMapping(value = "/borrowBook", method = RequestMethod.POST)
    public String borrowBook(@CookieValue String student,
                               @ModelAttribute LibraryInfoVo.LibraryBook book){
        book.setBorrowTime(dateFormat.format(new Date()));
        StudentVo studentVo = JsonUtils.json2object(student, StudentVo.class);
        ServiceResult<Void> result = libraryService.addBorrowBook(studentVo, book);
        if (!result.isSuccess())
            log.warn("摊上大事了");
        return "redirect: /web/information/library.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public WebResult updateInfo(@CookieValue String student,
                                  @RequestBody LibraryInfoVo libraryInfoVo){
        StudentVo studentVo = JsonUtils.json2object(student, StudentVo.class);
        ServiceResult<Void> result = libraryService.updateLibraryInfo(studentVo, libraryInfoVo);
        if (!result.isSuccess()) {
            log.warn("摊上大事了");
            return new WebResult<>(ResultCode.FIELD);
        }
        return new WebResult();
    }
}
