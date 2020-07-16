package cn.kgc.service;

import cn.kgc.dao.UserMapper;
import cn.kgc.entity.*;
import cn.kgc.util.MySendsms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2020/3/29.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login_getAuthList(User user) {
        User login = userMapper.login(user);
        return login;
    }

    @Override
    public List<Auth> queryAuthByPid(Integer id, Integer pid) {
        return userMapper.queryAuthByPid(id, pid);
    }

    @Override
    public List<Role> queryRoles(User user) {
        return userMapper.queryRoles(user);
    }

    @Override
    public List<User> queryUserByPhone(String phone) {
        return userMapper.queryUserByPhone(phone);
    }

    @Override
    public String yzm(String phone) {
        return MySendsms.getSms(phone);
        //return MySendsms.testSms(phone);
    }

    @Override
    @Transactional
    public Integer register(User user) {
        Integer addUser = userMapper.registerOfParent(user);
        //一个用户可能有多个角色,遍历完善user_role表
        Integer perpect = perpect(user);
        //如果都没出错，return 1。若出错,return -1
        return addUser * perpect==1?1:-1;
    }

    @Override
    public Integer isBind(String sNum, String pNum) {
        return userMapper.isBind(sNum, pNum);
    }

    @Override
    public User queryUser(User user, Integer rid) {
        return userMapper.queryUser(user, rid);
    }

    @Override
    @Transactional
    public Integer bindStudent(String sNum, String pNum, Integer status) {
        //首先刷新家长状态
        Integer updateParentStatus = userMapper.updateParentStatus(pNum, status);
        //接着完善student_parent表
        Integer perfectSP = userMapper.perfectSP(sNum, pNum);
        return perfectSP;
    }

    @Override
    public Integer improveInformation(User user) {
        return userMapper.improveInformation(user);
    }

    @Override
    public List<Clazz> getClazzList(String num,Integer flag) {
        return userMapper.getClazzList(num,flag);
    }

    @Override
    public List<Course> queryCourseByCid(Integer cId,Integer flag) {
        return userMapper.queryCourseByCid(cId,flag);
    }

    @Override
    public String getUserName(String num) {
        return userMapper.getUserName(num);
    }

    @Override
    public User queryChild(User user) {
        return userMapper.queryChild(user);
    }

    //完善user_role表,并且开启事务
    @Transactional
    public Integer perpect(User user) {
        Integer count = 1;
        String num = user.getNum();
        List<Role> roleList = user.getRoleList();
        for (Role role : roleList) {
            count *= userMapper.perfect(num, role.getId());
        }
        return count;
    }
}
