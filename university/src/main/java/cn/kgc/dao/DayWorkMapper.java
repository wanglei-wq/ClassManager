package cn.kgc.dao;

import cn.kgc.entity.DayWork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2020/5/13.
 */
public interface DayWorkMapper {
    //公布作业
    Integer publish(DayWork dayWork);

    //查询某班级每日作业
    List<DayWork> queryDaywork(@Param("classNum")String classNum,@Param("day")String day);
}
