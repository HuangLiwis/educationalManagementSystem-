package com.xiaozhi.serviceImpl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.xiaozhi.dao.mongo.FinancialDao;
import com.xiaozhi.exception.ServiceException;
import com.xiaozhi.model.FinancialVo;
import com.xiaozhi.model.StudentVo;
import com.xiaozhi.model.mongo.FinancialDo;
import com.xiaozhi.result.ResultCode;
import com.xiaozhi.result.resultImpl.ServiceResult;
import com.xiaozhi.service.FinancialService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 小智 on 2017/4/26 0026.
 */
@Service("financialService")
public class FinancialServiceImpl implements FinancialService{
    @Resource
    private FinancialDao financialDao;

    @Override
    public ServiceResult<FinancialVo> findFinancial(StudentVo studentVo){
        if (studentVo.isIllegal()) {
            throw new ServiceException(ResultCode.PARAMETER_ILLEGAL);
        }
        FinancialVo financialVo = new FinancialVo();
        FinancialDo financialDo = new FinancialDo();
        financialDo.setId(studentVo.getId());
        List<FinancialDo> financialDos = financialDao.find(financialDo);
        if (CollectionUtils.isEmpty(financialDos)) {
            return new ServiceResult<>(ResultCode.DATABASE_RETURN_NULL);
        }
        financialDo = financialDos.get(0);
        BeanUtils.copyProperties(financialDo, financialVo);
        return new ServiceResult<>(financialVo);
    }

    @Override
    public ServiceResult<Void> addMoneyRecord(StudentVo studentVo, FinancialVo.MoneyRecord moneyRecord){
        if (studentVo.isIllegal()) {
            throw new ServiceException(ResultCode.PARAMETER_ILLEGAL);
        }
        FinancialDo FinancialDo = new FinancialDo();
        FinancialDo.setId(studentVo.getId());
        FinancialDo = financialDao.find(FinancialDo).get(0);

        FinancialDo.MoneyRecord moneyRecordDo = new FinancialDo.MoneyRecord();
        BeanUtils.copyProperties(moneyRecord, moneyRecordDo);
        moneyRecordDo.setOverage(FinancialDo.getMoney() - moneyRecord.getFee());
        FinancialDo.setMoneyRecord(moneyRecordDo);
        FinancialDo.setMoney(FinancialDo.getMoney() - moneyRecord.getFee());

        financialDao.update(FinancialDo);

        return new ServiceResult<>();
    }

    @Override
    public ServiceResult<Void> updateFinancial(StudentVo studentVo, FinancialVo financialVo){
        if (studentVo.isIllegal()) {
            throw new ServiceException(ResultCode.PARAMETER_ILLEGAL);
        }
        return null;
    }
}
