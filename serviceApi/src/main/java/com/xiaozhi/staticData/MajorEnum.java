package com.xiaozhi.staticData;

/**
 * Created by 小智 on 2017/4/18 0018.
 * 专业数据对应
 */
public enum MajorEnum {
    SoftwareEngineering(1,"软件工程",AcademyEnum.Conputer.getAcademyId()),
    ComputerScience(2,"计算机科学",AcademyEnum.Conputer.getAcademyId()),
    ArtificialIntelligence(3,"人工智能",AcademyEnum.Conputer.getAcademyId())
    ;

    private int majorId;                  //专业号（自定）
    private String majorDescribe;         //专业描述
    private int belongToAcademy;          //所属学院 @See AcademyEnum

    MajorEnum(int majorId, String majorDescribe, int belongToAcademy) {
        this.majorId = majorId;
        this.majorDescribe = majorDescribe;
        this.belongToAcademy = belongToAcademy;
    }

    public int getMajorId() {
        return majorId;
    }

    public String getMajorDescribe() {
        return majorDescribe;
    }

    public int getBelongToAcademy() {
        return belongToAcademy;
    }
}
