package cn.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2020/5/8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Integer cId;
    private String cName;
    private Integer isScience;

    public Course(String cName) {
        this.cName = cName;
    }
}
