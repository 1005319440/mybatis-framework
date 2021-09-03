package cn.zfy.mybatis.framework.sqlnode.support;

import cn.zfy.mybatis.framework.sqlnode.DynamicContext;
import cn.zfy.mybatis.framework.sqlnode.SqlNode;

/**
 * @Classname TextSqlNode
 * @Description 存储带有 ${} 的文本信息
 * @Date 2021/9/3 12:11
 * @Created by zfy
 */
public class TextSqlNode implements SqlNode {

    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    public boolean isDynamic() {
        return sqlText.indexOf("${") > -1;
    }

    @Override
    public void apply(DynamicContext context) {

    }
}
