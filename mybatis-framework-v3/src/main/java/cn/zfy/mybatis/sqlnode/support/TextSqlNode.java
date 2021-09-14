package cn.zfy.mybatis.sqlnode.support;

import cn.zfy.mybatis.sqlnode.DynamicContext;
import cn.zfy.mybatis.sqlnode.SqlNode;
import cn.zfy.mybatis.utils.GenericTokenParser;
import cn.zfy.mybatis.utils.OgnlUtils;
import cn.zfy.mybatis.utils.SimpleTypeRegistry;
import cn.zfy.mybatis.utils.TokenHandler;

/**
 * @Classname TextSqlNode
 * @Description 存储带有 ${} 的文本信息
 * @Date 2021/9/7 14:16
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
        BindingTokenHandler tokenHandler = new BindingTokenHandler(context);
        GenericTokenParser tokenParser = new GenericTokenParser("${", "}", tokenHandler);
        context.appendSql(tokenParser.parse(sqlText));
    }

    private class BindingTokenHandler implements TokenHandler {

        private DynamicContext dynamicContext;

        public BindingTokenHandler(DynamicContext context) {
            this.dynamicContext = context;
        }

        @Override
        public String handleToken(String content) {
            //获取入参对象
            Object parameter = dynamicContext.getBindings().get("_parameter");
            //判断入参类型
            if (null == parameter) return "";
            if (SimpleTypeRegistry.isSimpleType(parameter.getClass())) return String.valueOf(parameter);
            Object value = OgnlUtils.getValue(content, parameter);
            return null == value ? "" : String.valueOf(value);
        }
    }
}
