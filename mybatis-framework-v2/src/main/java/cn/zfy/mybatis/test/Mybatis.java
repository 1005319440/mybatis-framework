package cn.zfy.mybatis.test;

import cn.zfy.mybatis.framework.config.Configuration;
import cn.zfy.mybatis.framework.config.MappedStatement;
import cn.zfy.mybatis.framework.sqlnode.SqlNode;
import cn.zfy.mybatis.framework.sqlnode.support.IfSqlNode;
import cn.zfy.mybatis.framework.sqlnode.support.MixedSqlNode;
import cn.zfy.mybatis.framework.sqlnode.support.StaticTextSqlNode;
import cn.zfy.mybatis.framework.sqlnode.support.TextSqlNode;
import cn.zfy.mybatis.framework.sqlsource.SqlSource;
import cn.zfy.mybatis.framework.sqlsource.support.DynamicSqlSource;
import cn.zfy.mybatis.framework.sqlsource.support.RawSqlSource;
import cn.zfy.mybatis.pojo.User;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * @Classname Mybatis V2
 * @Description properties 配置文件升级为xml配置文件
 * 使用面向过程思维优化代码
 * @Date 2021/9/1 11:31
 * @Created by zfy
 */
public class Mybatis {

    private Configuration configuration = new Configuration();

    private String namespace;

    private boolean isDynamic = false;

    /**
     * 查询
     */
    @Test
    public void testSelectList() throws Exception {
        loadXml("mybatis-config.xml");
        User user = new User();
        user.setId(1);
        user.setUsername("飞跃");
        List<User> userList = selectList("test.queryUserById", user);
        System.out.println(userList);
    }

    private <T> List<T> selectList(String statementId, Object params) throws NoSuchFieldException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<T> resultList = new ArrayList<>();
        try {
            //创建数据库连接
            connection = getConnection(configuration);

            //sql语句
            String sql = getSql();

            //获取预处理 statement
            MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);
            preparedStatement = connection.prepareStatement(sql);
            setParameter(preparedStatement, params, mappedStatement);

            resultSet = preparedStatement.executeQuery();
            handleResultSet(resultSet, resultList, mappedStatement);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) connection.close();
                if (null != resultSet) resultSet.close();
                if (null != preparedStatement) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Collections.EMPTY_LIST;
    }

    private <T> void handleResultSet(ResultSet resultSet, List<T> resultList, MappedStatement mappedStatement) {

    }

    private void setParameter(PreparedStatement preparedStatement, Object params, MappedStatement mappedStatement) {
    }

    private String getSql() {
        return null;
    }

    private Connection getConnection(Configuration configuration) {
        DataSource dataSource = configuration.getDataSource();
        try {
            Connection connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void loadXml(String location) throws Exception {
        InputStream inputStream = getResourceAsStream(location);
        Document document = createDocument(inputStream);
        //针对document对象，按照mybatis配置文件的语义去挨个解析
        parseConfiguration(document.getRootElement());
    }

    private void parseConfiguration(Element rootElement) throws Exception {
        Element environments = rootElement.element("environments");
        parseEnvironments(environments);
        Element mappers = rootElement.element("mappers");
        parseMappers(mappers);
    }

    private void parseEnvironments(Element environmentsElement) throws Exception {
        String aDefault = environmentsElement.attributeValue("default");
        if (null == aDefault || "".equals(aDefault)) throw new Exception("未知默认数据源");
        List<Element> environmentList = environmentsElement.elements("environment");
        environmentList.forEach(environmentElement -> {
            String id = environmentElement.attributeValue("id");
            if (aDefault.equals(id)) {
                parseDatasource(environmentElement.element("dataSource"));
            }
        });

    }

    private void parseDatasource(Element dataSourceElement) {
        String type = dataSourceElement.attributeValue("type");
        if ("DBCP".equalsIgnoreCase(type)) {
            BasicDataSource dataSource = new BasicDataSource();
            Properties properties = parseProperties(dataSourceElement.elements("property"));
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
            dataSource.setUrl(properties.getProperty("url"));
            configuration.setDataSource(dataSource);
        }
    }

    private Properties parseProperties(List<Element> propertyElementList) {
        Properties properties = new Properties();
        propertyElementList.forEach(propertyElement -> {
            String name = propertyElement.attributeValue("name");
            String value = propertyElement.attributeValue("value");
            properties.setProperty(name, value);
        });
        return properties;
    }

    private void parseMappers(Element mappersElement) {
        List<Element> mapperElementList = mappersElement.elements("mapper");
        if (null == mapperElementList || 0 == mapperElementList.size()) return;
        mapperElementList.forEach(mapperElement -> {
            String resource = mapperElement.attributeValue("resource");
            InputStream inputStream = getResourceAsStream(resource);
            Document mapperDocument = createDocument(inputStream);
            Element mapper = mapperDocument.getRootElement();
            parseMapper(mapper);
        });
    }

    private void parseMapper(Element mapper) {
        namespace = mapper.attributeValue("namespace");
        List<Element> scriptElementList = mapper.elements("select");
        scriptElementList.forEach(selectElement -> parseSelectElement(selectElement));
    }

    private void parseSelectElement(Element selectElement) {
        String statementId = namespace.concat(selectElement.attributeValue("id"));
        String parameterType = selectElement.attributeValue("parameterType");
        String resultType = selectElement.attributeValue("resultType");
        String statementType = selectElement.attributeValue("statementType");
        SqlSource sqlSource = createSqlSource(selectElement);
        MappedStatement mappedStatement = new MappedStatement.Builder()
                .setStatementId(statementId)
                .setStatementType(null == statementType || "".equals(statementType) ? "" : statementType)
                .setParameterTypeClass(resolveType(parameterType))
                .setResultTypeClass(resolveType(resultType))
                .setSqlSource(sqlSource)
                .build();
        configuration.addMappedStatement(statementId, mappedStatement);
    }

    private Class<?> resolveType(String parameterType) {
        try {
            return Class.forName(parameterType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private SqlSource createSqlSource(Element selectElement) {
        return parseScriptNode(selectElement);
    }

    private SqlSource parseScriptNode(Element selectElement) {
        //解析所有的sqlNode
        SqlNode mixedSqlNode = parseDynamicTags(selectElement);
        //将所有的sqlNode封装到SqlSource中
        SqlSource sqlSource = null;
        if (isDynamic) {
            sqlSource = new DynamicSqlSource(mixedSqlNode);
        } else {
            sqlSource = new RawSqlSource(mixedSqlNode);
        }
        return sqlSource;
    }

    private MixedSqlNode parseDynamicTags(Element tagsElement) {
        List<SqlNode> sqlNodes = new ArrayList<>();
        int nodeCount = tagsElement.nodeCount();
        for (int i = 0; i < nodeCount; i++) {
            Node node = tagsElement.node(i);
            if (node instanceof Text) {
                String text = node.getText();
                if (null == text || "".equals(text)) continue;
                TextSqlNode textSqlNode = new TextSqlNode(text);
                if (textSqlNode.isDynamic()) {
                    isDynamic = true;
                    sqlNodes.add(textSqlNode);
                } else {
                    sqlNodes.add(new StaticTextSqlNode(text));//文本标签
                }
            } else if (node instanceof Element) {
                isDynamic = true;
                Element element = (Element) node;
                String tagsName = element.getName();
                if ("if".equals(tagsName)) {
                    String testValue = element.attributeValue("test");
                    MixedSqlNode mixedSqlNode = parseDynamicTags(element);
                    IfSqlNode ifSqlNode = new IfSqlNode(testValue, mixedSqlNode);
                    sqlNodes.add(ifSqlNode);
                } else if ("where".equals(tagsName)) {

                } else {
                    //...
                }
            }
        }
        return new MixedSqlNode(sqlNodes);
    }

    private Document createDocument(InputStream inputStream) {
        try {
            SAXReader saxReader = new SAXReader();
            return saxReader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    private InputStream getResourceAsStream(String location) {
        return this.getClass().getClassLoader().getResourceAsStream(location);
    }

}
