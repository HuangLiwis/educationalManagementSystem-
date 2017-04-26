package com.xiaozhi.dao.mongo;

import com.xiaozhi.model.mongo.FinancialDo;

import java.util.List;

/**
 * Created by 小智 on 2017/4/18 0018.
 * 学生基本信息
 */
public interface FinancialDao{
    void save(FinancialDo financialDo);

    List<FinancialDo> find(FinancialDo financialDo);

    void update(FinancialDo financialDo);

    void delete(FinancialDo financialDo);
}
