package com.xiaozhi.model;

import com.xiaozhi.staticData.AcademyEnum;
import com.xiaozhi.staticData.MajorEnum;
import com.xiaozhi.staticData.StudentCommonEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by 小智 on 2017/4/29 0029.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentView implements Serializable{
    private String id;                 //学号
    private String name;               //姓名
    private String password;           //密码
    private String sex;               //性别
    private String education;         //学位
    private String academy;           //学院
    private String grade;             //班级
    /**
     * @see com.xiaozhi.staticData.AcademyEnum
     * @see MajorEnum
     * @see com.xiaozhi.staticData.StudentCommonEnum
     */
    private String phoneNumber;         //电话号
    private String peopleId;            //身份证号

    public StudentView(StudentVo studentVo){
        this.id = studentVo.getId();
        this.name = studentVo.getName();
        this.password = studentVo.getPassword();
        this.sex = StudentCommonEnum.getDescribeByCode(studentVo.getSex());
        this.education = StudentCommonEnum.getDescribeByCode(studentVo.getEducation());
        this.academy = AcademyEnum.getDescribeByCode(studentVo.getAcademy());
        this.grade = MajorEnum.getDescribeByCode(studentVo.getAcademy());
        this.phoneNumber = studentVo.getPhoneNumber();
        this.peopleId = studentVo.getPeopleId();
    }
}
