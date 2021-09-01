package cn.zfy.mybatis.test;

import cn.zfy.mybatis.pojo.User;
import cn.zfy.mybatis.utils.SimpleTypeRegistry;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

/**
 * @Classname MybatisV1
 * @Description 解决硬编码问题 （properties 文件）
 * @Date 2021/9/1 11:31
 * @Created by zfy
 */
public class Mybatis {


    /**
     * 存储 jdbc 配置文件中的内容
     */
    private Properties properties = new Properties();

    /**
     * 查村用户
     */
    @Test
    public void testSelectList() throws NoSuchFieldException {
        loadProperties("jdbc.properties");
       /* Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("id", 1);*/
        List<User> userList = selectList("queryUserById", 1);
        System.out.println(userList);
    }

    private void loadProperties(String location) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(location);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private <T> List<T> selectList(String statementId, Object params) throws NoSuchFieldException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName(properties.getProperty("db.driver"));

            //创建数据库连接
            connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.username"), properties.getProperty("db.password"));

            //sql语句
            String sql = properties.getProperty("db.sql." + statementId);

            //获取预处理 statement
            preparedStatement = connection.prepareStatement(sql);

            String paramNames;
            if (SimpleTypeRegistry.isSimpleType(params.getClass())) {
                preparedStatement.setObject(1, params);
            } else if (params instanceof Map) {
                paramNames = properties.getProperty("db.sql." + statementId + ".params");
                String[] paramsArr = paramNames.split(",");
                Map<String, Object> paramsMap = (Map) params;
                boolean hasAllParams = true;
                for (int i = 0; i < paramsArr.length; i++) {
                    String paramName = paramsArr[i];
                    if (!paramsMap.containsKey(paramName)) throw new RuntimeException("该statement参数缺失:" + paramName);
                    preparedStatement.setObject(i + 1, paramsMap.get(paramName));
                }
            } else {
                //todo  其他引用类型
            }
            resultSet = preparedStatement.executeQuery();
            List<T> resultList = new ArrayList<>();
            String returnClassName = properties.getProperty("db.sql." + statementId + ".returnClassName");
            Class<?> returnClass = Class.forName(returnClassName);
            Object result = null;
            while (resultSet.next()) {
                result = returnClass.newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    Field field = returnClass.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(result, resultSet.getObject(columnName));
                }
                resultList.add((T) result);
            }
            return resultList;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return Collections.EMPTY_LIST;
    }

}
