package cn.zfy.mybatis.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.*;

/**
 * @Classname JDBCDemo
 * @Description jdbc demo
 * @Date 2021/9/1 11:36
 * @Created by zfy
 */
public class JDBCDemo {


    @Test
    public void testJdbc() {
        Connection connection = null;

        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            //创建数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis_learn?characterEncoding=utf-8", "root", "mysql");

            //sql语句
            String sql = "select * from user where username = ?";

            //获取预处理 statement
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "飞跃");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + resultSet.getString("username"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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
    }


}
