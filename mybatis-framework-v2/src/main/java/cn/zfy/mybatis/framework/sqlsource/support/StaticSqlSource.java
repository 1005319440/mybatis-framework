package cn.zfy.mybatis.framework.sqlsource.support;

import cn.zfy.mybatis.framework.sqlsource.BoundSql;
import cn.zfy.mybatis.framework.sqlsource.ParameterMapping;
import cn.zfy.mybatis.framework.sqlsource.SqlSource;

import java.util.List;

/**
 * @Classname StaticSqlSource
 * @Description TODO
 * @Date 2021/9/7 10:03
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
