package cn.zfy.mybatis.builder;

import cn.zfy.mybatis.config.Configuration;
import cn.zfy.mybatis.config.MappedStatement;
import cn.zfy.mybatis.sqlsource.SqlSource;
import cn.zfy.mybatis.utils.ReflectUtils;
import org.dom4j.Element;

/**
 * @Classname XMLStatementBuilder
 * @Description 解析select等statement标签
 * @Date 2021/9/7 14:29
 * @Created by zfy
 */
public class XMLStatementBuilder {

    private Configuration configuration;

    public XMLStatementBuilder(Configuration configuration) {
        this.configuration = configuration;
    }


    public void parseStatementElement(String namespace, Element selectElement) {
        String id = selectElement.attributeValue("id");
        if (null == id || "".equals(id)) try {
            throw new Exception("statement's id cannot be null");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String statementId = namespace.concat(".").concat(id);

        String parameterType = selectElement.attributeValue("parameterType");
        Class<?> parameterClass = ReflectUtils.resolveType(parameterType);

        String resultType = selectElement.attributeValue("resultType");
        Class<?> resultClass = ReflectUtils.resolveType(resultType);

        String statementType = selectElement.attributeValue("statementType");
        statementType = null == statementType || "".equals(statementType) ? "prepared" : statementType;

        SqlSource sqlSource = createSqlSource(selectElement);

        MappedStatement mappedStatement = new MappedStatement.Builder()
                .setStatementId(statementId)
                .setResultTypeClass(resultClass)
                .setParameterTypeClass(parameterClass)
                .setSqlSource(sqlSource)
                .setStatementType(statementType)
                .build();
        configuration.addMappedStatement(statementId, mappedStatement);
    }

    private SqlSource createSqlSource(Element selectElement) {
        XMLScriptBuilder xmlScriptBuilder = new XMLScriptBuilder();
        return xmlScriptBuilder.parseScriptNode(selectElement);
    }
}
