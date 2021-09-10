package cn.zfy.mybatis.statement;

import cn.zfy.mybatis.config.MappedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Classname ResultSetHandler
 * @Description TODO
 * @Date 2021/9/10 16:47
 * @Created by zfy
 */
public interface ResultSetHandler {
    <T> void handResultSet(ResultSet resultSet, List<T> resultList, MappedStatement mappedStatement) throws Exception;
}
