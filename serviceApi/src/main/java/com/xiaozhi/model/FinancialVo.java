package com.xiaozhi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 小智 on 2017/4/26 0026.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialVo implements Serializable{
    private String id;                       //学号
    private Double money;                    //当前金额
    private MoneyRecord moneyRecord;         //某次消费记录
    private List<MoneyRecord> moneyRecords;  //消费记录列表

    @Data
    public static class MoneyRecord implements Serializable{
        private String time;                 //消费时间
        private String type;                 //消费类型
        private Double fee;                  //消费金额
        private Double overage;              //消费后余额
    }
}
