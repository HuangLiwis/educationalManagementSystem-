package com.xiaozhi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by 小智 on 2017/4/27 0027.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseVo implements Serializable{
    private String id;             //课程代码
    private String name;           //课程名称
    private String teacherName;    //老师姓名
    private String time;           //开课起止时间
    private String place;          //上课地点
    private String score;           //学分
    private Integer total;         //总量
    private Integer selected;      //已选
    private Integer remain;        //余量
    private String academy;        //开课学院
}
