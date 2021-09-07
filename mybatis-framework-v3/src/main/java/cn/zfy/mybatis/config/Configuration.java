package cn.zfy.mybatis.config;

import lombok.Data;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname Configuration
 * @Description 全局配置文件
 * @Date 2021/9/7 13:58
 * @Created by zfy
 */
@Data
public class Configuration {


    private DataSource dataSource;

    private Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public MappedStatement getMappedStatementById(String statementId) {
        return this.mappedStatements.get(statementId);
    }

    public void addMappedStatement(String statementId, MappedStatement mappedStatement) {
        this.mappedStatements.put(statementId, mappedStatement);
    }


}
