package cn.kgc.service;

import cn.kgc.entity.ClazzFee;

import java.util.List;

/**
 * Created by Administrator on 2020/5/9.
 */
public interface ClazzFeeService {
    //添加班费
    Integer addClassFee(ClazzFee clazzFee);

    //查询某班级班费的Y-M列表
    List<String> onlyMonth(String cNum);

    //根据month,cNum查询班费片段
    List<ClazzFee> queryClazzFee(String month,String cNum);

    //查询班费剩余
    Double queryClazzRemain(String cNum);

    //查询分母
    Integer fenmu(Integer fid);
    //查询分子
    Integer fenzi(Integer fid);

    //查询学生是否交了此班费
    Integer isFee(Integer fid,String num);

    //查询已交某班费的学生名称
    List<String> names(Integer fid, Integer status);

    //交班费
    Integer payFee(Integer fid, String num,Double money);
}
