package cn.kgc.service;

import cn.kgc.dao.DayWorkMapper;
import cn.kgc.entity.DayWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2020/5/13.
 */
@Service
public class DayWorkServiceImpl implements DayWorkService {
    @Autowired
    private DayWorkMapper dayWorkMapper;
    @Override
    public Integer publish(DayWork dayWork,String[] clazz) {
        Integer result = 0;
        for (String s : clazz) {
            dayWork.setClassNum(s.trim());
            Integer publish = dayWorkMapper.publish(dayWork);
        }
        return 1;
    }

    @Override
    public List<DayWork> queryDaywork(String classNum, String day) {
        return dayWorkMapper.queryDaywork(classNum, day);
    }
}
