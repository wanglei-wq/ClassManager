package cn.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2020/3/29.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth {
    private Integer id;
    private String urlname;
    private String url;
    private String pid;
}
