package cn.kgc.dao;

import cn.kgc.entity.ClazzFee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2020/5/9.
 */
public interface ClazzFeeMapper {
    //添加班费@classfee
    Integer addClassFee(ClazzFee clazzFee);

    //查询班费id@classfee
    ClazzFee queryClazzFeeByNumAndStartTime(ClazzFee clazzFee);

    //维护@classfee_student
    Integer addClazzFeeStudent(@Param("fid")Integer fid,@Param("num")String num);

    //查询某班级班费的Y-M列表
    List<String> onlyMonth(@Param("cNum") String cNum);

    //根据month,cNum查询班费片段
    List<ClazzFee> queryClazzFeeByMonthAndcNum(@Param("month")String month,@Param("cNum")String cNum);

    //查询班级班费剩余
    Double queryClazzRemain(@Param("cNum") String cNum);

    //查询分母
    Integer fenmu(@Param("fid")Integer fid);

    //查询分子
    Integer fenzi(@Param("fid") Integer fid);

    //查询学生是否交了此班费
    Integer isFee(@Param("fid")Integer fid,@Param("num")String num);

    //查询已交某班费的学生名称
    List<String> names(@Param("fid") Integer fid, @Param("status") Integer status);

    //修改班费未交状态
    Integer payFee(@Param("fid") Integer fid, @Param("num") String num);

    //addRemain
    Integer addRemain(@Param("money") Double money, @Param("fid") Integer fid);

}
