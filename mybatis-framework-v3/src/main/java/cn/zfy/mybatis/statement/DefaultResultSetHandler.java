package cn.zfy.mybatis.statement;


import cn.zfy.mybatis.config.MappedStatement;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

/**
 * @Classname DefaultResultSetHandler
 * @Description TODO
 * @Date 2021/9/10 16:49
 * @Created by zfy
 */
public class DefaultResultSetHandler implements ResultSetHandler {


    @Override
    public <T> void handResultSet(ResultSet resultSet, List<T> resultList, MappedStatement mappedStatement) throws Exception {
        Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
        Object result = null;
        while (resultSet.next()) {
            result = resultTypeClass.newInstance();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnName = metaData.getColumnName(i + 1);
                Field field = resultTypeClass.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(result, resultSet.getObject(columnName));
            }
            resultList.add((T) result);
        }
    }
}
