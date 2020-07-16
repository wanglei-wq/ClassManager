package cn.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2020/5/9.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzFee {
    private Integer id;
    private String classNum;
    private String num;
    private double money;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    private String description;
}
