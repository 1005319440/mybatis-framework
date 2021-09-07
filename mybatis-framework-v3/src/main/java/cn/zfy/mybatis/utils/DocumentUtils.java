package cn.zfy.mybatis.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @Classname DocumentUtils
 * @Description 创建文本对象
 * @Date 2021/9/7 14:23
 * @Created by zfy
 */
public class DocumentUtils {


    public static Document createDocument(InputStream inputStream) {
        SAXReader saxReader = new SAXReader();
        try {
            return saxReader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }


}
