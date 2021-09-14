package cn.zfy.mybatis.config;

import cn.zfy.mybatis.executor.CachingExecutor;
import cn.zfy.mybatis.executor.Executor;
import cn.zfy.mybatis.executor.SimpleExecutor;
import cn.zfy.mybatis.statement.*;
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

    private boolean useCache = false;

    public MappedStatement getMappedStatementById(String statementId) {
        return this.mappedStatements.get(statementId);
    }

    public void addMappedStatement(String statementId, MappedStatement mappedStatement) {
        this.mappedStatements.put(statementId, mappedStatement);
    }


    public Executor newExecutor(String type) {
        Executor executor = null;
        //真正的执行器是谁，需要通过 配置来指定，如果不指定，就默认是SimpleExecutor
        type = null == type || "".equals(type) ? "simple" : type;
        if ("simple".equalsIgnoreCase(type)) executor = new SimpleExecutor();
        if (!useCache) return executor;
        return new CachingExecutor(executor);
    }

    public StatementHandler newStatementHandler(String statementType) {
        StatementHandler statementHandler = null;
        if ("prepared".equalsIgnoreCase(statementType))
            statementHandler = new PreparedStatementHandler(this);
        return statementHandler;
    }

    public ParameterHandler newParameterHandler() {
        return new DefaultParameterHandler();
    }


    public ResultSetHandler newResultSetHandler() {
        return new DefaultResultSetHandler();
    }
}
