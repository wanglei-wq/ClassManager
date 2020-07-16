package cn.kgc.dao;

import cn.kgc.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2020/3/29.
 *
 * 用户方面
 */
public interface UserMapper {
    //用户登录
    User login(User user);

    //根据主权限id查询子权限
    List<Auth> queryAuthByPid(@Param("id") Integer id, @Param("pid") Integer pid);

    //查询用户所属角色
    List<Role> queryRoles(User user);

    //查询用户是否注册
    List<User> queryUserByPhone(@Param("phone") String phone);

    //1.家长注册
    Integer registerOfParent(User user);

    //user_role表完善
    Integer perfect(@Param("num") String num,@Param("rid") Integer roleId);

    //2.学生绑定
    //查询某家长与某学生是否已经绑定了
    Integer isBind(@Param("sNum") String sNum, @Param("pNum") String pNum);

    //查询是某一学生信息
    User queryUser(@Param("user") User user, @Param("rid") Integer rid);

    //修改家长注册状态
    Integer updateParentStatus(@Param("pNum") String pNum, @Param("status") Integer status);

    //完善student_parent表
    Integer perfectSP(@Param("sNum") String sNum, @Param("pNum") String pNum);

    //3.完善信息
    Integer improveInformation(User user);

    //查询用户所在班级列表
    List<Clazz> getClazzList(@Param("num") String num, @Param("flag") Integer flag);

    //查询教师所教科目
    List<Course> queryCourseByCid(@Param("cId") Integer cId, @Param("flag") Integer flag);

    //根据班级查询某种角色用户列表
    List<User> queryRoleUserByClazz(@Param("cNum")String cNum,@Param("rid")Integer rid);

    //根据num获得用户名称
    String getUserName(@Param("num")String num);

    //查询孩子
    User queryChild(User user);
}
