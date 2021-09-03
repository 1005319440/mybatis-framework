package cn.zfy.mybatis.framework.sqlsource.support;

import cn.zfy.mybatis.framework.sqlnode.SqlNode;
import cn.zfy.mybatis.framework.sqlsource.BoundSql;
import cn.zfy.mybatis.framework.sqlsource.SqlSource;

/**
 * @Classname RawSqlSource
 * @Description 封装解析出来的sql信息
 *               包含非动态标签和#{}
 * @Date 2021/9/3 11:29
 * @Created by zfy
 */
public class RawSqlSource implements SqlSource {


    public RawSqlSource(SqlNode rootSqlNode){
        // 解析 #{}
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return null;
    }
}
