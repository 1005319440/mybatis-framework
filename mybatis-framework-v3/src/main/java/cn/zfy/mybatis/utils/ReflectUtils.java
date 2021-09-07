package cn.zfy.mybatis.utils;

/**
 * @Classname ReflectUtils
 * @Description TODO
 * @Date 2021/9/7 15:58
 * @Created by zfy
 */
public class ReflectUtils {

    public static Class<?> resolveType(String parameterType) {
        try {
            Class<?> clazz = Class.forName(parameterType);
            return clazz;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
