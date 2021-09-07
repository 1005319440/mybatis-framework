package cn.zfy.mybatis.builder;

import cn.zfy.mybatis.config.Configuration;
import org.dom4j.Element;

import java.util.List;

/**
 * @Classname XMLMapperBuilder
 * @Description 解析 Mapper 映射文件
 * @Date 2021/9/7 14:28
 * @Created by zfy
 */
public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(Element rootElement) {
        String namespace = rootElement.attributeValue("namespace");
        if (null == namespace || "".equals(namespace))
            try {
                throw new Exception("Mapper's namespace cannot be empty");
            } catch (Exception e) {
                e.printStackTrace();
            }
        List<Element> selectElements = rootElement.elements("select");
            selectElements.forEach(selectElement->{
                XMLStatementBuilder xmlStatementBuilder = new XMLStatementBuilder(configuration);
                xmlStatementBuilder.parseStatementElement(namespace,selectElement);
            });
    }
}
