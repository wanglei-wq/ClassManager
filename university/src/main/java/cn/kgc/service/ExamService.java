package cn.kgc.service;

import cn.kgc.entity.Exam;

/**
 * Created by Administrator on 2020/5/8.
 */
public interface ExamService {
    //添加考试并完善相关表 @exam\ @exam_class \ @exam_course
    Integer addExam(Exam exam);

    //考试是否存在
    Integer isExist(String name);
}
