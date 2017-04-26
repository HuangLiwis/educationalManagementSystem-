package com.xiaozhi.daoImpl.mongo;

import com.google.common.base.Strings;
import com.xiaozhi.dao.mongo.LibraryInfoDao;
import com.xiaozhi.dao.mongo.StudentDao;
import com.xiaozhi.model.mongo.LibraryInfoDo;
import com.xiaozhi.model.mongo.StudentDo;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 小智 on 2017/4/18 0018.
 */
@Component
public class LibraryInfoDaoImpl implements LibraryInfoDao{
    @Resource
    private Datastore datastore;


    @Override
    public void save(LibraryInfoDo libraryInfoDo){
        datastore.save(libraryInfoDo);
    }

    @Override
    public List<LibraryInfoDo> find(LibraryInfoDo libraryInfoDo){
        Query<LibraryInfoDo> query = datastore.createQuery(LibraryInfoDo.class);
        if (!Strings.isNullOrEmpty(libraryInfoDo.getId())) {
            query.criteria("id").equal(libraryInfoDo.getId());
        }
        if (null != libraryInfoDo.getNotReturns()) {
            query.criteria("notReturns").equal(libraryInfoDo.getNotReturns());
        }
        if (null != libraryInfoDo.getOverdraft()) {
            query.criteria("overdraft").equal(libraryInfoDo.getOverdraft());
        }
        return query.asList();
    }

    @Override
    public void update(LibraryInfoDo libraryInfoDo){
        Query<LibraryInfoDo> oldClickEvent = datastore.createQuery(LibraryInfoDo.class)
                .field("id").equal(libraryInfoDo.getId());
        UpdateOperations<LibraryInfoDo> newClickEvent = datastore.createUpdateOperations(LibraryInfoDo.class);
        if (null != libraryInfoDo.getBorrows()) {
            newClickEvent.set("borrows", libraryInfoDo.getBorrows());
        }
        if (null != libraryInfoDo.getOverdraft()) {
            newClickEvent.set("overdraft", libraryInfoDo.getOverdraft());
        }
        if (null != libraryInfoDo.getNotReturns()) {
            newClickEvent.set("notReturns", libraryInfoDo.getNotReturns());
        }
        if (null != libraryInfoDo.getViolates()) {
            newClickEvent.set("violates", libraryInfoDo.getViolates());
        }
        if (null != libraryInfoDo.getLibraryBook()) {
            newClickEvent.addToSet("libraryBooks", libraryInfoDo.getLibraryBook());
        }
        datastore.updateFirst(oldClickEvent, newClickEvent);
    }

    @Override
    public void delete(LibraryInfoDo libraryInfoDo){

    }

}
