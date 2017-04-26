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
@Entity(value = "campusNetDo", noClassnameStored = true)
public class CampusNetDo implements Serializable{
    @Id
    private String id;                      //学号
    private Integer status;                  //当前状态
    private Double overage;                //余额
    private Integer currentChargeType;      //当前计费组
    private Integer nextChargeType;         //下个计费组
    private Integer usedTimes;              //已用时长
    private Integer usedFlows;              //已用流量
}
