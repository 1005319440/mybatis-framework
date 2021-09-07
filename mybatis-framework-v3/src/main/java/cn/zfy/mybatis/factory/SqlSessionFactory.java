package cn.zfy.mybatis.factory;

import cn.zfy.mybatis.sqlsession.SqlSession;

/**
 * @Classname SqlSessionFactory
 * @Description 创建SqlSession
 * @Date 2021/9/7 11:29
 * @Created by zfy
 */
public interface SqlSessionFactory {


    SqlSession openSession();

}
