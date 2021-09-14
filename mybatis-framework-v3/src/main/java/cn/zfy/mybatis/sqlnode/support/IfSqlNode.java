package cn.zfy.mybatis.sqlnode.support;

import cn.zfy.mybatis.sqlnode.DynamicContext;
import cn.zfy.mybatis.sqlnode.SqlNode;
import cn.zfy.mybatis.utils.OgnlUtils;

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
        boolean parameter = OgnlUtils.evaluateBoolean(test, context.getBindings().get("_parameter"));
        if (!parameter) return;
        mixedSqlNode.apply(context);
    }
}
