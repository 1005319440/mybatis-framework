package cn.zfy.mybatis.io;

import java.io.InputStream;

/**
 * @Classname Resources
 * @Description TODO
 * @Date 2021/9/7 14:25
 * @Created by zfy
 */
public class Resources {

    public static InputStream getResourceAsStream(String location) {
        return Resources.class.getClassLoader().getResourceAsStream(location);
    }

}
