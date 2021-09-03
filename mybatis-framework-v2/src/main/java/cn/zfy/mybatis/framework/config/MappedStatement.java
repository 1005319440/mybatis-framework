package cn.zfy.mybatis.framework.config;

import cn.zfy.mybatis.framework.sqlsource.SqlSource;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname MappedStatement
 * @Description crud标签  select|insert|update|delete标签封装成 MappedStatement对象
 * @Date 2021/9/2 9:56
 * @Created by zfy
 */
@Data
@NoArgsConstructor
public class MappedStatement {

    private String statementId;

    private String statementType;

    private Class<?> parameterTypeClass;

    private Class<?> resultTypeClass;

    private SqlSource sqlSource;

    public MappedStatement(Builder builder) {
        this.statementId = builder.statementId;
        this.statementType = builder.statementType;
        this.parameterTypeClass = builder.parameterTypeClass;
        this.resultTypeClass = builder.resultTypeClass;
        this.sqlSource = builder.sqlSource;
    }

    public static class Builder {
        private String statementId;
        private String statementType;
        private Class<?> parameterTypeClass;
        private Class<?> resultTypeClass;
        private SqlSource sqlSource;

        public Builder setStatementId(String statementId) {
            this.statementId = statementId;
            return this;
        }

        public Builder setStatementType(String statementType) {
            this.statementType = statementType;
            return this;
        }

        public Builder setParameterTypeClass(Class<?> parameterTypeClass) {
            this.parameterTypeClass = parameterTypeClass;
            return this;
        }

        public Builder setResultTypeClass(Class<?> resultTypeClass) {
            this.resultTypeClass = resultTypeClass;
            return this;
        }

        public Builder setSqlSource(SqlSource sqlSource) {
            this.sqlSource = sqlSource;
            return this;
        }

        public MappedStatement build(){
            return new MappedStatement(this);
        }
    }

}
