package cn.zfy.mybatis.sqlsession;

import java.util.List;

/**
 * @Classname SqlSession
 * @Description sqlSession接口 crud接口
 * @Date 2021/9/7 11:25
 * @Created by zfy
 */
public interface SqlSession {

    <T> List<T> selectList(String statementId, Object param);

    <T> T selectOne(String statementId, Object param);

}
