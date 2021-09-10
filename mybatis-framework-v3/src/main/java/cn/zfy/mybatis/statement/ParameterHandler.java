package cn.zfy.mybatis.statement;

import cn.zfy.mybatis.sqlsource.BoundSql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Classname ParameterHandler
 * @Description TODO
 * @Date 2021/9/10 16:47
 * @Created by zfy
 */
public interface ParameterHandler {
    void setParameter(PreparedStatement preparedStatement, Object param, BoundSql boundSql) throws SQLException;
}
