package cn.zfy.mybatis.factory;

import cn.zfy.mybatis.config.Configuration;
import cn.zfy.mybatis.sqlsession.DefaultSqlSession;
import cn.zfy.mybatis.sqlsession.SqlSession;

/**
 * @Classname DefaultSqlSessionFactory
 * @Description TODO
 * @Date 2021/9/7 11:30
 * @Created by zfy
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
