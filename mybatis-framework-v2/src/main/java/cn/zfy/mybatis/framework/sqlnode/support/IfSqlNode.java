package cn.zfy.mybatis.framework.sqlnode.support;

import cn.zfy.mybatis.framework.sqlnode.DynamicContext;
import cn.zfy.mybatis.framework.sqlnode.SqlNode;

/**
 * @Classname IfSqlNode
 * @Description 存储if标签的sql文本信息
 * @Date 2021/9/3 12:12
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
