package cn.zfy.mybatis.sqlsession;

import cn.zfy.mybatis.config.Configuration;
import cn.zfy.mybatis.config.MappedStatement;
import cn.zfy.mybatis.executor.Executor;
import lombok.SneakyThrows;

import java.util.List;

/**
 * @Classname DefaultSqlSession
 * @Description TODO
 * @Date 2021/9/7 11:26
 * @Created by zfy
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);
        Executor executor = configuration.newExecutor(null);
        return executor.doQuery(configuration, mappedStatement, param);
    }

    @SneakyThrows
    @Override
    public <T> T selectOne(String statementId, Object param) {
        List<Object> selectList = this.selectList(statementId, param);
        if (null == selectList) return null;
        if (selectList.size() > 1)
            throw new Exception("To many results , expect get one , but get ".concat(String.valueOf(selectList.size())));
        return (T) selectList.get(0);
    }
}
