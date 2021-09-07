package cn.zfy.mybatis.framework.sqlsource.support;

import cn.zfy.mybatis.framework.sqlnode.DynamicContext;
import cn.zfy.mybatis.framework.sqlnode.SqlNode;
import cn.zfy.mybatis.framework.sqlsource.BoundSql;
import cn.zfy.mybatis.framework.sqlsource.SqlSource;
import cn.zfy.mybatis.utils.GenericTokenParser;
import cn.zfy.mybatis.utils.ParameterMappingTokenHandler;

/**
 * @Classname RawSqlSource
 * @Description 封装解析出来的sql信息
 * 包含非动态标签和#{}
 * @Date 2021/9/3 11:29
 * @Created by zfy
 */
public class RawSqlSource implements SqlSource {

    private SqlSource sqlSource;


    public RawSqlSource(SqlNode rootSqlNode) {
        //解析 #{}

        DynamicContext dynamicContext = new DynamicContext();
        rootSqlNode.apply(dynamicContext);
        String sqlText = dynamicContext.getSql();
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        String sql = tokenParser.parse(sqlText);
        sqlSource = new StaticSqlSource(sql, tokenHandler.getParameterMappings());
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return sqlSource.getBoundSql(param);
    }
}
