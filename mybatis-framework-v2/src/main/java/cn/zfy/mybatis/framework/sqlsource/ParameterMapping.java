package cn.zfy.mybatis.framework.sqlsource;

import lombok.NoArgsConstructor;

/**
 * @Classname ParameterMapping
 * @Description 封装#{}解析出来的参数名称以及参数类型
 * @Date 2021/9/3 11:23
 * @Created by zfy
 */
@NoArgsConstructor
public class ParameterMapping {


    private String name;

    private Class<?> type;

    public ParameterMapping(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
