package cn.zfy.mybatis.sqlsource.support;

import cn.zfy.mybatis.sqlnode.DynamicContext;
import cn.zfy.mybatis.sqlnode.SqlNode;
import cn.zfy.mybatis.sqlsource.BoundSql;
import cn.zfy.mybatis.sqlsource.SqlSource;
import cn.zfy.mybatis.utils.GenericTokenParser;
import cn.zfy.mybatis.utils.ParameterMappingTokenHandler;

/**
 * @Classname RawSqlSource
 * @Description 封装解析出来的sql信息 包含非动态标签和#{}
 * @Date 2021/9/7 14:11
 * @Created by zfy
 */
public class RawSqlSource implements SqlSource {

    private SqlSource sqlSource;

    public RawSqlSource(SqlNode rootSqlNode) {
        DynamicContext dynamicContext = new DynamicContext();
        rootSqlNode.apply(dynamicContext);
        String sqlText = dynamicContext.getSql();
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        sqlSource = new StaticSqlSource(tokenParser.parse(sqlText), tokenHandler.getParameterMappings());
    }


    @Override
    public BoundSql getBoundSql(Object param) {


        return sqlSource.getBoundSql(param);
    }

}
