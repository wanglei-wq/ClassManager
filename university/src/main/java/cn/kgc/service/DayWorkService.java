package cn.kgc.service;

import cn.kgc.entity.DayWork;

import java.util.List;

/**
 * Created by Administrator on 2020/5/13.
 */
public interface DayWorkService {
    //公布作业
    Integer publish(DayWork dayWork,String[] clazz);

    //
    List<DayWork> queryDaywork(String classNum,String day);
}
