package cn.zfy.mybatis.sqlnode.support;

import cn.zfy.mybatis.sqlnode.DynamicContext;
import cn.zfy.mybatis.sqlnode.SqlNode;

/**
 * @Classname IfSqlNode
 * @Description TODO
 * @Date 2021/9/7 14:12
 * @Created by zfy
 */
public class IfSqlNode implements SqlNode {

    private String test;

    private MixedSqlNode mixedSqlNode;

    public IfSqlNode(String test, MixedSqlNode mixedSqlNode) {
        this.test = test;
        this.mixedSqlNode = mixedSqlNode;
    }

    @Override
    public void apply(DynamicContext context) {

    }
}
