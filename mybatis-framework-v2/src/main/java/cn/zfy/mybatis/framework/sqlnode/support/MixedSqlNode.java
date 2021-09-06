package cn.zfy.mybatis.framework.sqlnode.support;

import cn.zfy.mybatis.framework.sqlnode.DynamicContext;
import cn.zfy.mybatis.framework.sqlnode.SqlNode;

import java.util.List;

/**
 * @Classname MixedSqlNode
 * @Description 存储同一级别的sql文本信息
 * @Date 2021/9/3 12:13
 * @Created by zfy
 */
public class MixedSqlNode implements SqlNode {

    private List<SqlNode> sqlNodes;

    public MixedSqlNode(List<SqlNode> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }

    @Override
    public void apply(DynamicContext context) {
        this.sqlNodes.forEach(sqlNode -> sqlNode.apply(context));
    }
}
