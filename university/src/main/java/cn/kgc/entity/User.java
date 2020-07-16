package cn.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Administrator on 2020/3/29.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String num;
    private String password;
    private String name;
    private String sex;
    private Integer age;
    private String phone;
    private Integer courseId;
    private Integer classId;
    private Integer status;
    //角色列表
    private List<Role> roleList;
    //权限列表
    private List<Auth> list;

    public User(String num, String password) {
        this.num = num;
        this.password = password;
    }
}
