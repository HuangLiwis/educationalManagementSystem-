package com.xiaozhi.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

@Data
public class TestVo implements Serializable {
    private String name;
    private String password;
}
