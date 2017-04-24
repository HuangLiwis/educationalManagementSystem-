package com.xiaozhi.controller;

import com.xiaozhi.model.LibraryInfoVo;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.result.BaseResult;
import com.xiaozhi.result.resultImpl.ServiceResult;
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
    public LibraryInfoVo studentLogin(@ModelAttribute StudentVo student){
        ServiceResult<LibraryInfoVo> result = libraryService.findLibraryInfo(student);
        LibraryInfoVo libraryInfoVo = null;
        if (result.isSuccess())
            libraryInfoVo = result.getData();
        return libraryInfoVo;
    }

    @RequestMapping(value = "/borrowBook", method = RequestMethod.POST)
    public String studentLogin(@CookieValue String student,
                               @ModelAttribute LibraryInfoVo.LibraryBook book){
        book.setBorrowTime(dateFormat.format(new Date()));
        StudentVo studentVo = JsonUtils.json2object(student, StudentVo.class);
        ServiceResult<Void> result = libraryService.addBorrowBook(studentVo, book);
        if (!result.isSuccess())
            log.warn("摊上大事了");
        // return "information/library.html";
        return "redirect: /web/information/library.html";
    }
}
