package cn.zfy.mybatis.executor;

import cn.zfy.mybatis.config.Configuration;
import cn.zfy.mybatis.config.MappedStatement;
import cn.zfy.mybatis.sqlsource.BoundSql;
import cn.zfy.mybatis.sqlsource.SqlSource;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname BaseExecutor
 * @Description TODO
 * @Date 2021/9/10 16:27
 * @Created by zfy
 */
public abstract class BaseExecutor implements Executor {

    private Map<String, List> oneLevelCache = new ConcurrentHashMap<>();

    @Override
    public <T> List<T> doQuery(Configuration configuration, MappedStatement mappedStatement, Object param) {
        SqlSource sqlSource = mappedStatement.getSqlSource();
        BoundSql boundSql = sqlSource.getBoundSql(param);
        String sql = boundSql.getSql();
        //一级缓存处理
        List<T> list = oneLevelCache.get(sql);
        if (null == list) list = queryFromDataSource(configuration, mappedStatement, param, boundSql);
        oneLevelCache.put(sql, list);
        return list;
    }

    public abstract <T> List<T> queryFromDataSource(Configuration configuration, MappedStatement mappedStatement, Object param, BoundSql boundSql);
}
