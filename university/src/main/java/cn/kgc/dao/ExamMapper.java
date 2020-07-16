package cn.kgc.dao;

import cn.kgc.entity.Exam;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2020/5/8.
 *
 * 考试方面
 */
public interface ExamMapper {
    //添加考试@exam
    Integer addExam(Exam exam);

    //查询考试id @exam
    Exam queryExamByName(@Param("name") String name);

    //完善考试@exam_class
    Integer addExamClass(@Param("cNum") String cNum, @Param("examId") Integer examId);

    //完善考试@exam_course
    Integer addExamCourse(@Param("cName") String cName, @Param("examId") Integer examId);

    //考试是否存在
    Integer isExist(@Param("name") String name);
}
