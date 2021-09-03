package cn.zfy.mybatis.framework.sqlnode;

/**
 * @Classname SqlNode
 * @Description 提供对封装的sql脚本信息进行处理操作
 * @Date 2021/9/3 11:35
 * @Created by zfy
 */
public interface SqlNode {


    void apply(DynamicContext context);

}
