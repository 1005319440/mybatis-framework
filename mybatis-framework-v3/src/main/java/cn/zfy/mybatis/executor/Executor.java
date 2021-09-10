package cn.zfy.mybatis.executor;

import cn.zfy.mybatis.config.Configuration;
import cn.zfy.mybatis.config.MappedStatement;

import java.util.List;

/**
 * @Classname Executor
 * @Description sql执行器
 * @Date 2021/9/10 16:20
 * @Created by zfy
 */
public interface Executor {


    <T> List<T> doQuery(Configuration configuration, MappedStatement mappedStatement, Object param);


}
