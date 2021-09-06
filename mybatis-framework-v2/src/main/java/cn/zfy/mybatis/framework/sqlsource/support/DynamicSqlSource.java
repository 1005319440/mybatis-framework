package cn.zfy.mybatis.framework.sqlsource.support;

import cn.zfy.mybatis.framework.sqlnode.DynamicContext;
import cn.zfy.mybatis.framework.sqlnode.SqlNode;
import cn.zfy.mybatis.framework.sqlsource.BoundSql;
import cn.zfy.mybatis.framework.sqlsource.SqlSource;
import cn.zfy.mybatis.utils.GenericTokenParser;
import cn.zfy.mybatis.utils.ParameterMappingTokenHandler;
import cn.zfy.mybatis.utils.TokenHandler;

/**
 * @Classname DynamicSqlSource
 * @Description 封装解析出来的sql信息
 * 包含动态标签和 ${}
 * 注意事项: ${}需要每一次执行getBoundSql的时候，被解析一次
 * @Date 2021/9/3 11:28
 * @Created by zfy
 */
public class DynamicSqlSource implements SqlSource {


    //解析出来的所有的sqlNode节点信息
    private SqlNode rootSqlNode;

    public DynamicSqlSource(SqlNode rootSqlNode) {
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        DynamicContext dynamicContext = new DynamicContext(param);
        rootSqlNode.apply(dynamicContext);
        String sqlText = dynamicContext.getSql();
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        String sql = tokenParser.parse(sqlText);
        return new BoundSql(sql, tokenHandler.getParameterMappings());
    }
}
