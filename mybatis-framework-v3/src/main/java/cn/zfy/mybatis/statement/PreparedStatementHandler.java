package cn.zfy.mybatis.statement;

import cn.zfy.mybatis.config.Configuration;
import cn.zfy.mybatis.config.MappedStatement;
import cn.zfy.mybatis.sqlsource.BoundSql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname PreparedStatementHandler
 * @Description TODO
 * @Date 2021/9/10 16:41
 * @Created by zfy
 */
public class PreparedStatementHandler implements StatementHandler {

    private ParameterHandler parameterHandler;

    private ResultSetHandler resultSetHandler;


    public PreparedStatementHandler(Configuration configuration) {
        this.parameterHandler = configuration.newParameterHandler();
        this.resultSetHandler = configuration.newResultSetHandler();
    }

    @Override
    public Statement prepare(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    @Override
    public void parameterize(Statement statement, Object param, BoundSql boundSql) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        parameterHandler.setParameter(preparedStatement, param, boundSql);
    }

    @Override
    public <T> List<T> doQuery(Statement statement, MappedStatement mappedStatement) throws Exception {
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        ResultSet resultSet = preparedStatement.executeQuery();
        List<T> resultList = new ArrayList<>();
        resultSetHandler.handResultSet(resultSet, resultList, mappedStatement);
        return resultList;
    }
}
