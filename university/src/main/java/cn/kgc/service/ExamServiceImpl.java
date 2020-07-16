package cn.kgc.service;

import cn.kgc.dao.ExamMapper;
import cn.kgc.entity.Clazz;
import cn.kgc.entity.Course;
import cn.kgc.entity.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2020/5/8.
 */
@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    @Override
    @Transactional
    public Integer addExam(Exam exam) {
        //添加exam
        Integer count = examMapper.addExam(exam);
        //获得刚刚添加的exam的id
        Exam realExam = examMapper.queryExamByName(exam.getName());
        //完善表@exam_class
        List<Clazz> clazzList = exam.getClazzList();
        for (Clazz clazz : clazzList) {
            examMapper.addExamClass(clazz.getCNum(), realExam.getId());
        }
        //完善考试@exam_course
        List<Course> courseList = exam.getCourseList();
        for (Course course : courseList) {
            examMapper.addExamCourse(course.getCName(), realExam.getId());
        }
        return count;
    }

    @Override
    public Integer isExist(String name) {
        return examMapper.isExist(name);
    }
}
