package com.xiaozhi.daoImpl.mongo;

import com.google.common.base.Strings;
import com.xiaozhi.dao.mongo.StudentDao;
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
public class StudentDaoImpl implements StudentDao {
    @Resource
    private Datastore datastore;

    public void save(StudentDo studentDo) {
        datastore.save(studentDo);
    }

    public List<StudentDo> find(StudentDo studentDo) {
        Query<StudentDo> query=datastore.createQuery(StudentDo.class);
        if(!Strings.isNullOrEmpty(studentDo.getId())){
            query.criteria("id").equal(studentDo.getId());
        }
        if(!Strings.isNullOrEmpty(studentDo.getPassword())){
            query.criteria("password").equal(studentDo.getPassword());
        }
        if(null!=studentDo.getAcademy()){
            query.criteria("academy").equal(studentDo.getAcademy());
        }
        if(null!=studentDo.getGrade()){
            query.criteria("grade").equal(studentDo.getGrade());
        }
        return query.asList();
    }

    public void update(StudentDo studentDo) {
        Query<StudentDo> oldClickEvent = datastore.createQuery(StudentDo.class)
                .field("id").equal(studentDo.getId());
        UpdateOperations<StudentDo> newClickEvent = datastore.createUpdateOperations(StudentDo.class);
        if(!Strings.isNullOrEmpty(studentDo.getPassword())){
            newClickEvent.set("password",studentDo.getPassword());
        }
        if(null!=studentDo.getAcademy()){
            newClickEvent.set("academy",studentDo.getAcademy());
        }
        if(null!=studentDo.getGrade()){
            newClickEvent.set("grade",studentDo.getGrade());
        }
        if(!Strings.isNullOrEmpty(studentDo.getName())){
            newClickEvent.set("name",studentDo.getName());
        }
        datastore.updateFirst(oldClickEvent, newClickEvent);
    }

    public void delete(StudentDo studentDo) {
        Query<StudentDo> query=datastore.createQuery(StudentDo.class);
        query.criteria("id").equal(studentDo.getId());
        datastore.findAndDelete(query);
    }
}
