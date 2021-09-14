package cn.zfy.mybatis.statement;

import cn.zfy.mybatis.config.MappedStatement;
import cn.zfy.mybatis.sqlsource.BoundSql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Classname StatementHandler
 * @Description TODO
 * @Date 2021/9/10 16:37
 * @Created by zfy
 */
public interface StatementHandler {

    Statement prepare(Connection connection, String sql) throws SQLException;

    void parameterize(Statement statement, Object param, BoundSql boundSql) throws SQLException;

    <T> List<T> doQuery(Statement statement, MappedStatement mappedStatement) throws Exception;
}
