package cn.kgc.controller;

import cn.kgc.entity.*;
import cn.kgc.service.UserService;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/3/29.
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    public UserService userService;
    //用户登录
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(@RequestParam(value = "num",defaultValue = "")String num,
                        @RequestParam(value = "password",defaultValue = "")String password, HttpServletRequest request) {
        System.out.println("访问ok");
        if (num != "" && password != "") {
            User user = userService.login_getAuthList(new User(num, password));
            if (user != null) {
                //向session中放入user
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(30 * 60);
                session.setAttribute("user",user);
                return JSON.toJSONString(user);
            }
        }
        return "0";
    }
    //查询主权限的子权限
    @RequestMapping(value = "/queryChildAuth",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryChildAuth(@RequestParam(value = "id")Integer pid,HttpServletRequest request) {
        //从session中取出user
        User user = (User) request.getSession().getAttribute("user");
        Integer id = user.getId();
        List<Auth> auths = userService.queryAuthByPid(id, pid);
        return JSON.toJSONString(auths);
    }
    //查询用户所属角色
    @RequestMapping(method = RequestMethod.POST,value = "/roleList",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String roleList(User user){
        List<Role> roleList = userService.queryRoles(user);
        return JSON.toJSONString(roleList);
    }
    //查询用户是否注册
    @RequestMapping(method = RequestMethod.POST,value = "/isRegister")
    @ResponseBody
    public String isRegister(@RequestParam(value = "phone")String phone){
        List<User> list = userService.queryUserByPhone(phone);
        if (list.size() != 0) {
            return "0";
        } else {
            return "1";
        }
    }
    //发送验证码
    @RequestMapping(method = RequestMethod.POST,value = "/yzm")
    @ResponseBody
    public String yzm(@RequestParam(value = "phone")String phone){
        System.out.println("发送验证码");
        return userService.yzm(phone);
    }
    //用户注册
    @RequestMapping(method = RequestMethod.POST,value = "/register")
    @ResponseBody
    public Integer register(User user, @Param("ids[]")Integer[] ids){
        //将phone同时设置成num
        user.setNum(user.getPhone());
        List<Role> roleList = new ArrayList<>();
        for (Integer id : ids) {
            roleList.add(new Role(id, ""));
        }
        user.setRoleList(roleList);
        System.out.println("家长注册"+user);
        return userService.register(user);
    }
    //查询某家长与某学生是否已经绑定了
    @RequestMapping(method = RequestMethod.POST,value = "/isBind")
    @ResponseBody
    public Integer isBind(@RequestParam("sNum")String sNum,
                         @RequestParam("pNum")String pNum){
        return userService.isBind(sNum, pNum);
    }
    //查询某一角色用户
    @RequestMapping(method = RequestMethod.POST,value = "/queryUser",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryUser(User user, @RequestParam("rid")Integer rid){
        User nowUser = userService.queryUser(user, rid);
        return JSON.toJSONString(nowUser);
    }
    //学生绑定
    @RequestMapping(method = RequestMethod.POST,value = "/bindStudent")
    @ResponseBody
    public Integer bindStudent(@RequestParam("sNum")String sNum,
                              @RequestParam("pNum")String pNum,
                              @RequestParam("status")Integer status){
        return userService.bindStudent(sNum, pNum, status);
    }
    //完善家长信息
    @RequestMapping(method = RequestMethod.POST,value = "/improveInformation")
    @ResponseBody
    public Integer improveInformation(User user){
        return userService.improveInformation(user);
    }
    //查询用户所在班级列表
    @RequestMapping(method = RequestMethod.POST,value = "/clazzList",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String clazzList(@RequestParam("num")String num,@RequestParam("flag")Integer flag){
        System.out.println("查询num:" + num + "flag:"+flag+"班级list");
        List<Clazz> clazzList = userService.getClazzList(num,flag);
        return JSON.toJSONString(clazzList);
    }
    //查询教师所教科目
    @RequestMapping(method = RequestMethod.POST,value = "/subjectList",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String subjectList(@RequestParam("cId")Integer cId,@RequestParam("flag")Integer flag){
        System.out.println("查询cId:" + cId + "对应科目");
        List<Course> courseList = userService.queryCourseByCid(cId,flag);
        return JSON.toJSONString(courseList);
    }
    //根据num查询名字
    @RequestMapping(method = RequestMethod.POST,value = "/getUserName",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserName(@RequestParam("num")String num){
        return JSON.toJSONString(userService.getUserName(num));
    }
    //
    @RequestMapping(method = RequestMethod.POST,value = "/queryChild",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryChild(@RequestParam(value = "num",defaultValue = "")String num,
                             @RequestParam(value = "password",defaultValue = "")String password){
        return JSON.toJSONString(userService.queryChild(new User(num,password)));
    }
}
