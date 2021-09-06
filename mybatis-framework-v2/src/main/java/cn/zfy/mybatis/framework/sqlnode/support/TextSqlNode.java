package cn.zfy.mybatis.framework.sqlnode.support;

import cn.zfy.mybatis.framework.sqlnode.DynamicContext;
import cn.zfy.mybatis.framework.sqlnode.SqlNode;
import cn.zfy.mybatis.utils.GenericTokenParser;
import cn.zfy.mybatis.utils.OgnlUtils;
import cn.zfy.mybatis.utils.SimpleTypeRegistry;
import cn.zfy.mybatis.utils.TokenHandler;
import sun.reflect.generics.tree.SimpleClassTypeSignature;

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
        // #{}中的内容被GenericTokenParser解析出来之后，需要TokenHandler进行处理
        BindingTokenHandler tokenHandler = new BindingTokenHandler(context);
        GenericTokenParser tokenParser = new GenericTokenParser("${", "}", tokenHandler);
        context.appendSql(tokenParser.parse(sqlText));
    }

    /**
     * 用来处理${}中的参数问题
     */
    class BindingTokenHandler implements TokenHandler {

        private DynamicContext dynamicContext;

        public BindingTokenHandler(DynamicContext dynamicContext) {
            this.dynamicContext = dynamicContext;
        }


        /**
         * @param content ${}中的参数名称
         * @return ${} 对应的参数值（如果是简单类型，则直接返回，如果是Map类型，需要特殊处理）
         */
        @Override
        public String handleToken(String content) {
            //获取入参对象
            Object parameter = dynamicContext.getBindings().get("_parameter");
            //判断入参类型
            if (null == parameter) return "";
            if (SimpleTypeRegistry.isSimpleType(parameter.getClass())) return parameter.toString();
            Object value = OgnlUtils.getValue(content, parameter);
            return null == value ? "" : String.valueOf(value);
        }
    }
}
