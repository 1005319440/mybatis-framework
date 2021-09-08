package cn.zfy.mybatis.sqlsource;

import lombok.Data;

/**
 * @Classname ParameterMapping
 * @Description TODO
 * @Date 2021/9/7 14:06
 * @Created by zfy
 */
@Data
public class ParameterMapping {

    private String name;

    private Class<?> type;

    public ParameterMapping(String name) {
        this.name = name;
    }
}
