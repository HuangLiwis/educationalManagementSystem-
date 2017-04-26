package com.xiaozhi.daoImpl.mongo;

import com.google.common.base.Strings;
import com.xiaozhi.dao.mongo.FinancialDao;
import com.xiaozhi.model.mongo.FinancialDo;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 小智 on 2017/4/25 0025.
 */
@Component
public class FinancialDaoImpl implements FinancialDao{
    @Resource
    private Datastore datastore;

    @Override
    public void save(FinancialDo financialDo){
        datastore.save(financialDo);
    }

    @Override
    public List<FinancialDo> find(FinancialDo financialDo){
        Query<FinancialDo> query = datastore.createQuery(FinancialDo.class);
        if (!Strings.isNullOrEmpty(financialDo.getId())) {
            query.criteria("id").equal(financialDo.getId());
        }
        return query.asList();
    }

    @Override
    public void update(FinancialDo financialDo){
        Query<FinancialDo> oldClickEvent = datastore.createQuery(FinancialDo.class)
                .field("id").equal(financialDo.getId());
        UpdateOperations<FinancialDo> newClickEvent = datastore.createUpdateOperations(FinancialDo.class);
        if (null != financialDo.getMoney()) {
            newClickEvent.set("money", financialDo.getMoney());
        }
        if (null != financialDo.getMoneyRecord()) {
            newClickEvent.addToSet("moneyRecords", financialDo.getMoneyRecord());
        }
        datastore.updateFirst(oldClickEvent, newClickEvent);
    }

    @Override
    public void delete(FinancialDo financialDo){

    }
}
