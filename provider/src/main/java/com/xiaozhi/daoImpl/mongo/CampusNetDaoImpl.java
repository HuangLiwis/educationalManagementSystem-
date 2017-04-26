package com.xiaozhi.daoImpl.mongo;

import com.google.common.base.Strings;
import com.xiaozhi.dao.mongo.CampusNetDao;
import com.xiaozhi.model.mongo.CampusNetDo;
import com.xiaozhi.model.mongo.LibraryInfoDo;
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
public class CampusNetDaoImpl implements CampusNetDao{
    @Resource
    private Datastore datastore;

    @Override
    public void save(CampusNetDo campusNetDo){
        datastore.save(campusNetDo);
    }

    @Override
    public List<CampusNetDo> find(CampusNetDo campusNetDo){
        Query<CampusNetDo> query = datastore.createQuery(CampusNetDo.class);
        if (!Strings.isNullOrEmpty(campusNetDo.getId())) {
            query.criteria("id").equal(campusNetDo.getId());
        }
        return query.asList();
    }

    @Override
    public void update(CampusNetDo campusNetDo){
        Query<CampusNetDo> oldClickEvent = datastore.createQuery(CampusNetDo.class)
                .field("id").equal(campusNetDo.getId());
        UpdateOperations<CampusNetDo> newClickEvent = datastore.createUpdateOperations(CampusNetDo.class);
        if (null != campusNetDo.getOverage()) {
            newClickEvent.set("overage", campusNetDo.getOverage());
        }
        if (null != campusNetDo.getStatus()) {
            newClickEvent.set("status", campusNetDo.getStatus());
        }
        if (null != campusNetDo.getCurrentChargeType()) {
            newClickEvent.set("currentChargeType", campusNetDo.getCurrentChargeType());
        }
        if (null != campusNetDo.getNextChargeType()) {
            newClickEvent.set("nextChargeType", campusNetDo.getNextChargeType());
        }
        datastore.updateFirst(oldClickEvent, newClickEvent);
    }

    @Override
    public void delete(CampusNetDo campusNetDo){

    }
}
