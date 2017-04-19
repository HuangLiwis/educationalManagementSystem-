package com.xiaozhi.model.mongo;

import com.xiaozhi.staticData.MajorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.io.Serializable;

/**
 * Created by 小智 on 2017/4/18 0018.
 * 学生基本信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(value = "student",noClassnameStored = true) //不保存类名在文档中
public class StudentDo implements Serializable{
    @Id
    private String id;                 //学号
    private String name;               //姓名
    private String password;           //密码
    private Integer academy;           //学院
    private Integer grade;             //班级
    /**
     * @see com.xiaozhi.staticData.AcademyEnum
     * @see MajorEnum
     */
    private String phoneNumber;         //电话号
    private String peopleId;            //身份证号
}
