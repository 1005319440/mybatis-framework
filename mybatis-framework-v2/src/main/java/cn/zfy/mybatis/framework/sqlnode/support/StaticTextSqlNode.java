package cn.zfy.mybatis.framework.sqlnode.support;

import cn.zfy.mybatis.framework.sqlnode.DynamicContext;
import cn.zfy.mybatis.framework.sqlnode.SqlNode;

/**
 * @Classname TextSqlNode
 * @Description 存储带有 #{} 或者没有特殊字符的 的文本信息
 * @Date 2021/9/3 12:11
 * @Created by zfy
 */
public class StaticTextSqlNode implements SqlNode {

    private String sqlText;

    public StaticTextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext context) {
        context.appendSql(sqlText);
    }
}
