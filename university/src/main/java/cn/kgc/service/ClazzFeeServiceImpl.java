package cn.kgc.service;

import cn.kgc.dao.ClazzFeeMapper;
import cn.kgc.dao.UserMapper;
import cn.kgc.entity.ClazzFee;
import cn.kgc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2020/5/9.
 */
@Service
public class ClazzFeeServiceImpl implements ClazzFeeService {
    @Autowired
    private ClazzFeeMapper clazzFeeMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public Integer addClassFee(ClazzFee clazzFee) {
        //添加ClazzFee
        Integer addClassFee = clazzFeeMapper.addClassFee(clazzFee);
        //查询fid
        ClazzFee realClazzFee = clazzFeeMapper.queryClazzFeeByNumAndStartTime(clazzFee);
        Integer fid = realClazzFee.getId();
        //查询某班级所有学生
        List<User> userList = userMapper.queryRoleUserByClazz(clazzFee.getClassNum(), 1);
        //维护表@classfee_student
        for (User user : userList) {
            clazzFeeMapper.addClazzFeeStudent(fid, user.getNum());
        }
        return addClassFee;
    }

    @Override
    public List<String> onlyMonth(String cNum) {
        return clazzFeeMapper.onlyMonth(cNum);
    }

    @Override
    public List<ClazzFee> queryClazzFee(String month, String cNum) {
        return clazzFeeMapper.queryClazzFeeByMonthAndcNum(month, cNum);
    }

    @Override
    public Double queryClazzRemain(String cNum) {
        return clazzFeeMapper.queryClazzRemain(cNum);
    }

    @Override
    public Integer fenmu(Integer fid) {
        return clazzFeeMapper.fenmu(fid);
    }

    @Override
    public Integer fenzi(Integer fid) {
        return clazzFeeMapper.fenzi(fid);
    }

    @Override
    public Integer isFee(Integer fid, String num) {
        return clazzFeeMapper.isFee(fid, num);
    }

    @Override
    public List<String> names(Integer fid, Integer status) {
        List<String> names = clazzFeeMapper.names(fid, status);
        if (null == names || names.size() == 0) {
            if (status == 1) {
                names.add("暂时还没有学生交班费哦！");
            } else {
                names.add("班费已经收齐！");
            }
        }
        return names;
    }

    @Override
    @Transactional
    public Integer payFee(Integer fid, String num,Double money) {
        //修改缴费学生状态
        Integer payFee = clazzFeeMapper.payFee(fid, num);
        //添加班费
        Integer addRemain = clazzFeeMapper.addRemain(money, fid);
        return payFee * addRemain;
    }
}
