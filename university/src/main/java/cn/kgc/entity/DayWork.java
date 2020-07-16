package cn.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2020/5/13.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayWork {
    private Integer dId;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date day;
    private String cName;
    private String classNum;
}
