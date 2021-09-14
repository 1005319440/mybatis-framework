package cn.zfy.mybatis.sqlsource.support;

import cn.zfy.mybatis.sqlsource.BoundSql;
import cn.zfy.mybatis.sqlsource.ParameterMapping;
import cn.zfy.mybatis.sqlsource.SqlSource;

import java.util.List;

/**
 * @Classname StaticSqlSource
 * @Description TODO
 * @Date 2021/9/14 10:59
 * @Created by zfy
 */
public class StaticSqlSource implements SqlSource {


    private String sql;

    private List<ParameterMapping> parameterMappings;


    public StaticSqlSource(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return new BoundSql(sql, parameterMappings);
    }
}
