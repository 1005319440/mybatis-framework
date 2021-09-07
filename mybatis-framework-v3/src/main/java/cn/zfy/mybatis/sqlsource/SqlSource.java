package cn.zfy.mybatis.sqlsource;

/**
 * @Classname SqlSource
 * @Description sql源  一个 crud标签(mappedStatement)对应一个sqlSource
 *              获得一个BoundSql(其中包含了sql信息和参数信息)
 * @Date 2021/9/7 14:04
 * @Created by zfy
 */
public interface SqlSource {


    BoundSql getBoundSql(Object param);

}
