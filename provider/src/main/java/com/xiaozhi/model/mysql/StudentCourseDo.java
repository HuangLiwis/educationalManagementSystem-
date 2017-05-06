package com.xiaozhi.model.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.Serializable;

/**
 * Created by 小智 on 2017/4/28 0028.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseDo implements Serializable{
    private String studentId;
    private String courseId;
}
