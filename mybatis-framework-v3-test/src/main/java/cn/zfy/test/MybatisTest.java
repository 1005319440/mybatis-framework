package cn.zfy.test;

import cn.zfy.mybatis.builder.SqlSessionFactoryBuilder;
import cn.zfy.mybatis.factory.SqlSessionFactory;
import cn.zfy.mybatis.io.Resources;
import cn.zfy.mybatis.sqlsession.SqlSession;
import cn.zfy.pojo.User;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname MybatisTest
 * @Description 测试自定义框架
 * @Date 2021/9/8 10:04
 * @Created by zfy
 */
public class MybatisTest {


    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }


    @Test
    public void testSelectList() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", 1);
        paramMap.put("username", "飞跃");
        List<User> userList = sqlSession.selectList("test.queryUserById", paramMap);
        System.out.println(userList);

    }


}
