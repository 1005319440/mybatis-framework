package cn.zfy.mybatis.framework.sqlsource.support;

import cn.zfy.mybatis.framework.sqlnode.SqlNode;
import cn.zfy.mybatis.framework.sqlsource.BoundSql;
import cn.zfy.mybatis.framework.sqlsource.SqlSource;

/**
 * @Classname DynamicSqlSource
 * @Description 封装解析出来的sql信息
 *              包含动态标签和 ${}
 *              注意事项: ${}需要每一次执行getBoundSql的时候，被解析一次
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
        return null;
    }
}
