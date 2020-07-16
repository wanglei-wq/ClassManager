package cn.kgc.service;

import cn.kgc.entity.*;

import java.util.List;

/**
 * Created by Administrator on 2020/3/29.
 */
public interface UserService {
    //用户登录，获得权限列表
    User login_getAuthList(User user);

    //根据主权限id查询子权限
    List<Auth> queryAuthByPid(Integer id, Integer pid);

    //查询用户所属角色
    List<Role> queryRoles(User user);

    //查询用户是否注册
    List<User> queryUserByPhone(String phone);

    //发送yzm
    String yzm(String phone);

    //用户注册，同时完善user_role表
    Integer register(User user);

    //查询某家长与某学生是否已经绑定了
    Integer isBind(String sNum, String pNum);

    //查询某一角色的用户信息
    User queryUser(User user, Integer rid);

    //学生绑定
    Integer bindStudent(String sNum,String pNum,Integer status);

    //完善信息
    Integer improveInformation(User user);
    //查询用户所在班级列表
    List<Clazz> getClazzList(String num,Integer flag);
    //查询教师所教科目
    List<Course> queryCourseByCid(Integer cId,Integer flag);

    //根据num查询名字
    String getUserName(String num);

    //
    User queryChild(User user);
}
