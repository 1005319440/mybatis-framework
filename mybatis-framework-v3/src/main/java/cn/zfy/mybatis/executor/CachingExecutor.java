package cn.zfy.mybatis.executor;

import cn.zfy.mybatis.config.Configuration;
import cn.zfy.mybatis.config.MappedStatement;

import java.util.List;

/**
 * @Classname CachingExecutor
 * @Description TODO
 * @Date 2021/9/10 16:24
 * @Created by zfy
 */
public class CachingExecutor implements Executor {

    private Executor delegate;

    public CachingExecutor(Executor delegate) {
        this.delegate = delegate;
    }


    @Override
    public <T> List<T> doQuery(Configuration configuration, MappedStatement mappedStatement, Object param) {
        return delegate.doQuery(configuration, mappedStatement, param);
    }
}
