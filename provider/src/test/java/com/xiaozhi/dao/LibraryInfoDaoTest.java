package com.xiaozhi.dao;

import com.xiaozhi.dao.mongo.LibraryInfoDao;
import com.xiaozhi.dao.mongo.StudentDao;
import com.xiaozhi.model.mongo.LibraryInfoDo;
import com.xiaozhi.model.mongo.StudentDo;
import com.xiaozhi.staticData.AcademyEnum;
import com.xiaozhi.staticData.MajorEnum;
import com.xiaozhi.staticData.StudentCommonEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by 小智 on 2017/4/18 0018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class LibraryInfoDaoTest{
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Resource
    LibraryInfoDao libraryInfoDao;

    private LibraryInfoDo libraryInfoDo;

    @Before
    public void initObject(){
        libraryInfoDo = new LibraryInfoDo("1305120309", 0, 0, 0, 0, null, null);
    }

    @Test
    public void save(){
        libraryInfoDao.save(libraryInfoDo);
    }

    @Test
    public void update(){
        LibraryInfoDo.LibraryBook book = new LibraryInfoDo.LibraryBook();
        book.setName("计算机入门");
        book.setBookId("051301");
        book.setChargePerson("王大海");
        book.setBorrowTime(dateFormat.format(new Date()));
        libraryInfoDo.setLibraryBook(book);
        libraryInfoDao.update(libraryInfoDo);
    }

    @Test
    public void find(){
        libraryInfoDo = new LibraryInfoDo();
        libraryInfoDo.setId("1305120309");
        libraryInfoDao.find(libraryInfoDo).forEach(System.out::println);
    }
}
