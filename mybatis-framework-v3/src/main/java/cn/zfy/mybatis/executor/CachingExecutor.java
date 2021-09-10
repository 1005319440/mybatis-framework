package cn.zfy.mybatis.executor;

import cn.zfy.mybatis.config.Configuration;
import cn.zfy.mybatis.config.MappedStatement;
import cn.zfy.mybatis.sqlsource.BoundSql;

import java.util.List;

/**
 * @Classname CachingExecutor
 * @Description TODO
 * @Date 2021/9/10 16:24
 * @Created by zfy
 */
public class CachingExecutor extends BaseExecutor {

    @Override
    public <T> List<T> queryFromDataSource(Configuration configuration, MappedStatement mappedStatement, Object param, BoundSql boundSql) {
        return null;
    }
}
