package cn.zfy.mybatis.sqlnode;

/**
 * @Classname SqlNode
 * @Description
 * @Date 2021/9/7 14:08
 * @Created by zfy
 */
public interface SqlNode {


    void apply(DynamicContext context);



}
