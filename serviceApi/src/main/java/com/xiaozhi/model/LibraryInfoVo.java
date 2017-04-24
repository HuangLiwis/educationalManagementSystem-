package com.xiaozhi.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 小智 on 2017/4/22 0022.
 * <p>
 * 图书馆查询信息
 */
@Data
public class LibraryInfoVo implements Serializable{
    private String id;                 //学号
    private Integer violates;          //违章次数
    private Integer borrows;           //累计借阅次数
    private Integer overdraft;         //欠款金额
    private Integer notReturns;        //未还书籍数
    private LibraryBook libraryBook;   //当前借阅书籍
    private List<LibraryBook> libraryBooks;   //借阅书籍

    @Data
    public static class LibraryBook implements Serializable{
        private String bookId;           //书籍编号
        private String name;             //书名
        private String chargePerson;     //负责人
        private String borrowTime;       //借阅时间
        private String returnTime;       //归还时间
    }
}
