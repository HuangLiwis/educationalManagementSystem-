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
import java.text.SimpleDateFormat;
import java.util.Date;
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
        //老数据
        FinancialDo financialDo = new FinancialDo();
        financialDo.setId(studentVo.getId());
        financialDo = financialDao.find(financialDo).get(0);
        //插入记录，计算余额
        FinancialDo.MoneyRecord moneyRecordDo = new FinancialDo.MoneyRecord();
        BeanUtils.copyProperties(moneyRecord, moneyRecordDo);
        moneyRecordDo.setOverage(financialDo.getMoney() - moneyRecord.getFee());
        financialDo.setMoneyRecord(moneyRecordDo);
        financialDo.setMoney(financialDo.getMoney() - moneyRecord.getFee());

        financialDao.update(financialDo);
        return new ServiceResult<>();
    }

    @Override
    public ServiceResult<Void> updateFinancial(StudentVo studentVo, FinancialVo financialVo){
        if (studentVo.isIllegal()) {
            throw new ServiceException(ResultCode.PARAMETER_ILLEGAL);
        }
        FinancialDo financialDo = new FinancialDo();
        BeanUtils.copyProperties(financialVo, financialDo);
        //获取老数据
        FinancialDo oldFinancialDo = financialDao.find(financialDo).get(0);
        //增加记录
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FinancialDo.MoneyRecord moneyRecord = new FinancialDo.MoneyRecord();
        moneyRecord.setTime(dateFormat.format(new Date()));
        moneyRecord.setFee(financialVo.getMoney());
        moneyRecord.setType("总财务充值");
        moneyRecord.setOverage(financialVo.getMoney() + oldFinancialDo.getMoney());
        financialDo.setMoney(financialVo.getMoney() + oldFinancialDo.getMoney());
        financialDo.setMoneyRecord(moneyRecord);
        financialDao.update(financialDo);
        return new ServiceResult<>();
    }
}
