package com.xiaozhi.dao.mysql;

import com.xiaozhi.model.mysql.TestDo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangzh on 2017/1/13.
 */
@Repository
public interface TestDao {
    void addUser(TestDo testDo);
    //void updateUser(TestDo testDo);
    List<TestDo> findUser(TestDo testDo);
    //void deleteUser(Integer uid);
}
