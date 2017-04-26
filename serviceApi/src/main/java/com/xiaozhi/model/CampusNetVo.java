package com.xiaozhi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by 小智 on 2017/4/25 0025.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampusNetVo implements Serializable{
    private String id;                      //学号
    private Integer status;                  //当前状态
    private Double overage;                //余额
    private Integer currentChargeType;      //当前计费组
    private Integer nextChargeType;         //下个计费组
    private Integer usedTimes;              //已用时长
    private Integer usedFlows;              //已用流量
}
