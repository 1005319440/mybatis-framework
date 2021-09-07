package cn.zfy.mybatis.sqlnode.support;

import cn.zfy.mybatis.sqlnode.DynamicContext;
import cn.zfy.mybatis.sqlnode.SqlNode;

import java.util.List;

/**
 * @Classname MixedSqlNode
 * @Description 存储同一级别的sql文本信息
 * @Date 2021/9/7 14:17
 * @Created by zfy
 */
public class MixedSqlNode implements SqlNode {


    private List<SqlNode> sqlNodes;

    public MixedSqlNode(List<SqlNode> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }

    @Override
    public void apply(DynamicContext context) {

    }
}
