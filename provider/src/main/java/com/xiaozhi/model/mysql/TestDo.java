package com.xiaozhi.model.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by huangzh on 2017/1/13.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDo implements Serializable{
    private String name;
    private String password;
}
