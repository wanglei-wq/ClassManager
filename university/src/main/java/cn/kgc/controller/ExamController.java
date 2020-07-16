package cn.kgc.controller;

import cn.kgc.entity.Clazz;
import cn.kgc.entity.Course;
import cn.kgc.entity.Exam;
import cn.kgc.service.ExamService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/5/8.
 */
@Controller
@RequestMapping("exam")
public class ExamController {
    @Autowired
    public ExamService examService;

    //添加考试
    @RequestMapping(value = "/createExam",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Integer createExam(Exam exam,
                              @Param("clazz[]") String[] clazz,
                              @Param("course[]") String[] course){
        List<Clazz> clazzList = new ArrayList<>();
        List<Course> courseList = new ArrayList<>();
        for (String cla : clazz) {
            clazzList.add(new Clazz(cla));
        }
        for (String cour : course) {
            courseList.add(new Course(cour));
        }
        exam.setClazzList(clazzList);
        exam.setCourseList(courseList);
        //
        return examService.addExam(exam);
    }
    //考试是否存在
    @RequestMapping(value = "/isExist",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Integer isExist(@RequestParam("name")String name){
        return examService.isExist(name);
    }
}
