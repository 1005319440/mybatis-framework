package cn.zfy.mybatis.builder;

import cn.zfy.mybatis.config.Configuration;
import cn.zfy.mybatis.io.Resources;
import cn.zfy.mybatis.utils.DocumentUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @Classname XMLConfigBuilder
 * @Description 解析全局配置文件
 * @Date 2021/9/7 14:24
 * @Created by zfy
 */
public class XMLConfigBuilder {

    private Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    public Configuration parse(InputStream inputStream) {
        Document document = DocumentUtils.createDocument(inputStream);
        parseConfiguration(document.getRootElement());
        return configuration;
    }

    private void parseConfiguration(Element rootElement) {
        Element environments = rootElement.element("environments");
        parseEnvironments(environments);
        Element mappers = rootElement.element("mappers");
        parseMappers(mappers);
    }

    private void parseMappers(Element mappers) {
        List<Element> mapperList = mappers.elements("mapper");
        if (null == mapperList || mapperList.size() == 0) return;
        mapperList.forEach(element -> {
            //获取 mapper.xml的路径
            String resource = element.attributeValue("resource");
            Document document = DocumentUtils.createDocument(Resources.getResourceAsStream(resource));
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            xmlMapperBuilder.parse(document.getRootElement());
        });
    }

    private void parseEnvironments(Element environments) {
        String aDefault = environments.attributeValue("default");
        if (null == aDefault || "".equals(aDefault)) try {
            throw new Exception("please choose a default environment");
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Element> environmentElements = environments.elements("environment");
        environmentElements.forEach(element -> {
            if (aDefault.equals(element.attributeValue("id"))) {
                Element dsElement = element.element("dataSource");
                parseDataSource(dsElement);
            }
        });
    }

    private void parseDataSource(Element dsElement) {
        String type = dsElement.attributeValue("type");
        if ("DBCP".equalsIgnoreCase(type)) {
            BasicDataSource dataSource = new BasicDataSource();
            Properties properties = parseProperty(dsElement);
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setDriverClassName(properties.getProperty("driver"));
            configuration.setDataSource(dataSource);
        }
    }

    private Properties parseProperty(Element dsElement) {
        Properties properties = new Properties();
        List<Element> propertyList = dsElement.elements("property");
        propertyList.forEach(property -> properties.put(property.attributeValue("name"), property.attributeValue("value")));
        return properties;
    }
}
