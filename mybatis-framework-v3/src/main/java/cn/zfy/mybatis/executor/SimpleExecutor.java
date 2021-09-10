package cn.zfy.mybatis.executor;

import cn.zfy.mybatis.config.Configuration;
import cn.zfy.mybatis.config.MappedStatement;
import cn.zfy.mybatis.sqlsource.BoundSql;
import cn.zfy.mybatis.statement.StatementHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname SimpleExecutor
 * @Description TODO
 * @Date 2021/9/10 16:24
 * @Created by zfy
 */
public class SimpleExecutor extends BaseExecutor {

    @Override
    public <T> List<T> queryFromDataSource(Configuration configuration, MappedStatement mappedStatement, Object param, BoundSql boundSql) {
        Connection connection = null;


        try {
            //获取连接
            connection = getConnection(configuration);
            StatementHandler statementHandler = configuration.newStatementHandler(mappedStatement.getStatementType());
            Statement statement = statementHandler.prepare(connection, boundSql.getSql());
            statementHandler.parameterize(statement, param, boundSql);
            return statementHandler.doQuery(statement, mappedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection(Configuration configuration) {
        DataSource dataSource = configuration.getDataSource();
        try {
            Connection connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
