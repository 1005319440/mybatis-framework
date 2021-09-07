package cn.zfy.mybatis.sqlsession;

import lombok.SneakyThrows;

import java.util.List;

/**
 * @Classname DefaultSqlSession
 * @Description TODO
 * @Date 2021/9/7 11:26
 * @Created by zfy
 */
public class DefaultSqlSession implements SqlSession {
    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        return null;
    }

    @SneakyThrows
    @Override
    public <T> T getOne(String statementId, Object param) {
        List<Object> selectList = this.selectList(statementId, param);
        if (null == selectList) return null;
        if (selectList.size() > 1)
            throw new Exception("To many results , expect get one , but get ".concat(String.valueOf(selectList.size())));
        return (T) selectList.get(0);
    }
}
