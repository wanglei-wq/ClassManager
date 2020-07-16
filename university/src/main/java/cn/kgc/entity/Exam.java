package cn.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2020/5/8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    private Integer id;
    private String name;
    private String num;
    private String type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    //班级列表
    List<Clazz> clazzList;
    //科目列表
    List<Course> courseList;
}
