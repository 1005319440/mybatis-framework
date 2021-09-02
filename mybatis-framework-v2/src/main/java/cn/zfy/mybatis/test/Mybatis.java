package cn.zfy.mybatis.test;

import cn.zfy.mybatis.framework.config.Configuration;
import cn.zfy.mybatis.framework.config.MappedStatement;
import cn.zfy.mybatis.pojo.User;
import cn.zfy.mybatis.utils.SimpleTypeRegistry;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

/**
 * @Classname Mybatis V2
 * @Description properties 配置文件升级为xml配置文件
 * 使用面向过程思维优化代码
 * @Date 2021/9/1 11:31
 * @Created by zfy
 */
public class Mybatis {

    private Configuration configuration;

    /**
     * 存储 jdbc 配置文件中的内容
     */
    private Properties properties = new Properties();

    /**
     * 查村用户
     */
    @Test
    public void testSelectList() throws NoSuchFieldException {
        loadXml("mybatis-config.xml");
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("id", 1);
        paramsMap.put("username", "飞跃");
        List<User> userList = selectList("queryUserByIdAndUsername", paramsMap);
        System.out.println(userList);
    }

    private void loadXml(String location) {

    }

    private <T> List<T> selectList(String statementId, Object params) throws NoSuchFieldException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //创建数据库连接
            connection = getConnection(configuration);

            //sql语句
            String sql = getSql();

            //获取预处理 statement
            MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);
            preparedStatement = connection.prepareStatement(sql);
            setParameter(preparedStatement, params, mappedStatement);

            resultSet = preparedStatement.executeQuery();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) connection.close();
                if (null != resultSet) resultSet.close();
                if (null != preparedStatement) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Collections.EMPTY_LIST;
    }

    private <T> void handleResultSet(ResultSet resultSet, List<T> resultList, MappedStatement mappedStatement) {

    }

    private void setParameter(PreparedStatement preparedStatement, Object params, MappedStatement mappedStatement) {
    }

    private String getSql() {
        return null;
    }

    private Connection getConnection(Configuration configuration) {
        return null;
    }

}
