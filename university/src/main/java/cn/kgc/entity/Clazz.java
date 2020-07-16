package cn.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2020/5/8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz {
    private Integer cId;
    private String cNum;
    private String tNum;
    private Integer startNum;
    private Integer nowNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    private Double remain;
    private Integer status;
    //用户列表
    private List<User> userList;

    public Clazz(String cNum) {
        this.cNum = cNum;
    }
}
