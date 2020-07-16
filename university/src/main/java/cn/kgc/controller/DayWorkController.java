package cn.kgc.controller;

import cn.kgc.entity.DayWork;
import cn.kgc.service.DayWorkService;
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
 * Created by Administrator on 2020/5/13.
 */
@Controller
@RequestMapping("daywork")
public class DayWorkController {
    @Autowired
    public DayWorkService dayWorkService;

    @RequestMapping(value = "/publish",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String publish(@RequestParam("content")String content,
                          @Param("clazz[]")String[] clazz,
                          @Param("course[]")String[] course) throws Exception{
        DayWork dayWork = new DayWork();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String day = format.format(now);
        dayWork.setDay(format.parse(day));
        dayWork.setContent(content);
        dayWork.setCName(course[0]);
        Integer publish = dayWorkService.publish(dayWork, clazz);
        return publish+"";
    }
    @RequestMapping(value = "/queryDaywork",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryDaywork(@RequestParam("cNum")String cNum) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String day = format.format(now);
        List<DayWork> dayWorkList = dayWorkService.queryDaywork(cNum, day);
        return JSON.toJSONString(dayWorkList);
    }
}
