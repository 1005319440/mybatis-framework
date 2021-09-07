package cn.zfy.mybatis.builder;

import cn.zfy.mybatis.factory.DefaultSqlSessionFactory;
import cn.zfy.mybatis.factory.SqlSessionFactory;

import java.io.InputStream;
import java.io.Reader;

/**
 * @Classname SqlSessionFactoryBuilder
 * @Description
 * @Date 2021/9/7 11:31
 * @Created by zfy
 */
public class SqlSessionFactoryBuilder {


    public SqlSessionFactory build(InputStream inputStream) {
        return null;
    }

    public SqlSessionFactory build(Reader reader) {
        return null;
    }

    private SqlSessionFactory build() {
        return new DefaultSqlSessionFactory();
    }

}
