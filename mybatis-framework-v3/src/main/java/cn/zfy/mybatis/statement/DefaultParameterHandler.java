package cn.zfy.mybatis.statement;


import cn.zfy.mybatis.sqlsource.BoundSql;
import cn.zfy.mybatis.sqlsource.ParameterMapping;
import cn.zfy.mybatis.utils.SimpleTypeRegistry;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Classname DefaultParameterHandler
 * @Description TODO
 * @Date 2021/9/10 16:49
 * @Created by zfy
 */
public class DefaultParameterHandler implements ParameterHandler {


    @Override
    public void setParameter(PreparedStatement preparedStatement, Object param, BoundSql boundSql) throws SQLException {
        if (SimpleTypeRegistry.isSimpleType(param.getClass())) {
            preparedStatement.setObject(1, param);
        } else if (param instanceof Map) {
            Map<String, Object> paramsMap = (Map) param;
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                preparedStatement.setObject(i + 1, paramsMap.get(parameterMapping.getName()));
            }
        } else {

        }
    }
}
