package cn.zfy.mybatis.sqlsource;

import lombok.Data;

import java.util.List;

/**
 * @Classname BoundSql
 * @Description TODO
 * @Date 2021/9/7 14:06
 * @Created by zfy
 */
@Data
public class BoundSql {


    private String sql;

    private List<ParameterMapping> parameterMappings;

    public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }
}
