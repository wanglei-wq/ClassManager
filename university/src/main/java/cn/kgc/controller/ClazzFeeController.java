package cn.kgc.controller;

import cn.kgc.entity.ClazzFee;
import cn.kgc.service.ClazzFeeService;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2020/5/9.
 */
@Controller
@RequestMapping("classfee")
public class ClazzFeeController {
    @Autowired
    public ClazzFeeService clazzFeeService;

    //添加班费
    @RequestMapping(value = "/addClassFee",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Integer addClassFee(ClazzFee clazzFee,
                              @Param("clazz[]") String[] clazz) throws Exception{
        Integer count = 1;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String startTime = format.format(now);
        clazzFee.setStartTime(format.parse(startTime));
        for (String cla : clazz) {
            clazzFee.setClassNum(cla.trim());
            count *= clazzFeeService.addClassFee(clazzFee);
        }
        return count;
    }
    //查询某班级班费的Y-M列表
    @RequestMapping(value = "/onlyMonth",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String onlyMonth(@RequestParam("cNum")String cNum){
        List<String> list = clazzFeeService.onlyMonth(cNum);
        return JSON.toJSONString(list);
    }
    //根据month,cNum查询班费片段
    @RequestMapping(value = "/dayClazzFee",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String dayClazzFee(@RequestParam("month")String month,
                              @RequestParam("cNum")String cNum){
        List<ClazzFee> feeList = clazzFeeService.queryClazzFee(month, cNum);
        return JSON.toJSONString(feeList);
    }
    //查询班费剩余
    @RequestMapping(value = "/getClazzFee",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Double getClazzFee(@RequestParam("cNum")String cNum){
        return clazzFeeService.queryClazzRemain(cNum);
    }
    //查询分子、分母
    @RequestMapping(value = "/fenmu",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Integer fenmu(@RequestParam("fid")Integer fid){
        return clazzFeeService.fenmu(fid);
    }
    //查询分子、分母
    @RequestMapping(value = "/fenzi",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Integer fenzi(@RequestParam("fid")Integer fid){
        return clazzFeeService.fenzi(fid);
    }
    //查询学生是否交了此班费
    @RequestMapping(value = "/isFee",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Integer isFee(@RequestParam("fid")Integer fid,
                         @RequestParam("num")String num){
        return clazzFeeService.isFee(fid, num);
    }
    //查询已交某班费的学生名称
    @RequestMapping(value = "/names",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String names(@RequestParam("fid")Integer fid,
                         @RequestParam("status")Integer status){
        List<String> names = clazzFeeService.names(fid, status);
        return JSON.toJSONString(names);
    }
}
