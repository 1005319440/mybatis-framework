package cn.zfy.mybatis.sqlsource.support;

import cn.zfy.mybatis.sqlnode.SqlNode;
import cn.zfy.mybatis.sqlsource.BoundSql;
import cn.zfy.mybatis.sqlsource.SqlSource;

/**
 * @Classname RawSqlSource
 * @Description 封装解析出来的sql信息 包含非动态标签和#{}
 * @Date 2021/9/7 14:11
 * @Created by zfy
 */
public class RawSqlSource implements SqlSource {


    private SqlNode rootSqlNode;

    public RawSqlSource(SqlNode rootSqlNode) {
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return null;
    }
}
