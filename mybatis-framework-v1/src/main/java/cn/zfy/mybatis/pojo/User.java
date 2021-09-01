package cn.zfy.mybatis.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Classname User
 * @Description 测试实体 User
 * @Date 2021/9/1 11:33
 * @Created by zfy
 */
@Data
public class User {

    private Integer id;

    private String username;

    private Date birthday;

    private String sex;

    private String address;

}
