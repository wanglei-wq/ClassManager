package cn.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2020/4/26.
 */

/**
 * 1	学生
 2	家长
 3	教师
 4	班主任
 5	超级管理员
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Integer id;
    private String rolename;
}
