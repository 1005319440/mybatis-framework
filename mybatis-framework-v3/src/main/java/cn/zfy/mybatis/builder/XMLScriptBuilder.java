package cn.zfy.mybatis.builder;

import cn.zfy.mybatis.sqlnode.SqlNode;
import cn.zfy.mybatis.sqlnode.support.IfSqlNode;
import cn.zfy.mybatis.sqlnode.support.MixedSqlNode;
import cn.zfy.mybatis.sqlnode.support.StaticTextSqlNode;
import cn.zfy.mybatis.sqlnode.support.TextSqlNode;
import cn.zfy.mybatis.sqlsource.SqlSource;
import cn.zfy.mybatis.sqlsource.support.DynamicSqlSource;
import cn.zfy.mybatis.sqlsource.support.RawSqlSource;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname XMLScriptBuilder
 * @Description 解析SqlSource
 * @Date 2021/9/7 14:29
 * @Created by zfy
 */
public class XMLScriptBuilder {

    private boolean isDynamic = false;

    public SqlSource parseScriptNode(Element selectElement) {
        MixedSqlNode mixedSqlNode = parseDynamicTags(selectElement);
        if (isDynamic) return new DynamicSqlSource(mixedSqlNode);
        return new RawSqlSource(mixedSqlNode);
    }

    private MixedSqlNode parseDynamicTags(Element selectElement) {
        int nodeCount = selectElement.nodeCount();
        List<SqlNode> sqlNodes = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++) {
            Node node = selectElement.node(i);
            if (node instanceof Text) {
                String text = node.getText();
                if (null == text || "".equals(text)) continue;
                TextSqlNode textSqlNode = new TextSqlNode(text);
                if (!textSqlNode.isDynamic()) {
                    sqlNodes.add(new StaticTextSqlNode(text));
                    continue;
                }
                isDynamic = true;
                sqlNodes.add(new TextSqlNode(text));
            } else if (node instanceof Element) {
                isDynamic = true;
                Element element = (Element) node;
                String tagName = element.getName();
                if ("if".equals(tagName)) {
                    String test = element.attributeValue("test");
                    MixedSqlNode mixedSqlNode = parseDynamicTags(element);
                    IfSqlNode ifSqlNode = new IfSqlNode(test, mixedSqlNode);
                    sqlNodes.add(ifSqlNode);
                } else if ("where".equals(tagName)) {

                } else {

                }
            }
        }
        return new MixedSqlNode(sqlNodes);
    }
}
