package cn.zfy.mybatis.sqlsource.support;

import cn.zfy.mybatis.sqlnode.SqlNode;
import cn.zfy.mybatis.sqlsource.BoundSql;
import cn.zfy.mybatis.sqlsource.SqlSource;

/**
 * @Classname DynamicSqlSource
 * @Description 封装解析出来的sql信息, 包含动态标签和${} ,${}需要每执行一次getBoundSql时候,进行解析一次
 * @Date 2021/9/7 14:08
 * @Created by zfy
 */
public class DynamicSqlSource implements SqlSource {

    private SqlNode rootSqlNode;

    public DynamicSqlSource(SqlNode rootSqlNode) {
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return null;
    }
}
