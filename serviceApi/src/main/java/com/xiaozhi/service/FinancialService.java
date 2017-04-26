package com.xiaozhi.service;

import com.xiaozhi.model.FinancialVo;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.result.resultImpl.ServiceResult;

/**
 * Created by 小智 on 2017/4/22 0022.
 * <p>
 * 图书馆服务
 */
public interface FinancialService{
    /**
     * 查询By学号
     */
    ServiceResult<FinancialVo> findFinancial(StudentVo studentVo);

    /**
     * 增加借阅书籍
     */
    ServiceResult<Void> addMoneyRecord(StudentVo studentVo, FinancialVo.MoneyRecord moneyRecord);

    /**
     * 更新信息
     */
    ServiceResult<Void> updateFinancial(StudentVo studentVo, FinancialVo financialVo);
}
