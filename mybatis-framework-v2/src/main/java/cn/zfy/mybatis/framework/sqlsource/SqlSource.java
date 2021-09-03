package cn.zfy.mybatis.framework.sqlsource;

/**
 * @Classname SqlSource  对封装的sqlNode提供处理接口
 * @Description 一个statement 有一个sqlSource
 * 一个sqlSource 有多个 sqlNode  ,其中sqlNode是以树状结构实现的,嵌套那种
 *
 *
 * @Date 2021/9/2 9:59
 * @Created by zfy
 */
public interface SqlSource {

    /**
     * 解析封装好的sqlNode信息
     * @param param
     * @return
     */
    BoundSql getBoundSql(Object param);
}
