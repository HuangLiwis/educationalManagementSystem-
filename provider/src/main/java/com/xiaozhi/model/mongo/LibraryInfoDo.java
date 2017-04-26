package com.xiaozhi.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 小智 on 2017/4/22 0022.
 * <p>
 * 图书馆查询信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(value = "libraryInfo", noClassnameStored = true)
public class LibraryInfoDo implements Serializable{
    @Id
    private String id;                 //学号
    private Integer violates;          //违章次数
    private Integer borrows;           //累计借阅次数
    private Integer overdraft;         //欠款金额
    private Integer notReturns;        //未还书籍数
    private LibraryBook libraryBook = new LibraryBook();   //当前借阅书籍
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
