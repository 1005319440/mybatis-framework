package cn.zfy.mybatis.config;

import cn.zfy.mybatis.sqlsource.SqlSource;
import lombok.Data;

/**
 * @Classname MappedStatement
 * @Description crud 标签
 * @Date 2021/9/7 13:59
 * @Created by zfy
 */
@Data
public class MappedStatement {


    private String statementId;

    private Class<?> resultTypeClass;

    private Class<?> parameterTypeClass;

    private String statementType;

    private SqlSource sqlSource;

    public MappedStatement(Builder builder) {
        this.statementId = builder.statementId;
        this.resultTypeClass = builder.resultTypeClass;
        this.parameterTypeClass = builder.parameterTypeClass;
        this.statementType = builder.statementType;
        this.sqlSource = builder.sqlSource;
    }

    public static class Builder {

        private String statementId;

        private Class<?> resultTypeClass;

        private Class<?> parameterTypeClass;

        private String statementType;

        private SqlSource sqlSource;

        public Builder setStatementId(String statementId) {
            this.statementId = statementId;
            return this;
        }

        public Builder setResultTypeClass(Class<?> resultTypeClass) {
            this.resultTypeClass = resultTypeClass;
            return this;
        }

        public Builder setParameterTypeClass(Class<?> parameterTypeClass) {
            this.parameterTypeClass = parameterTypeClass;
            return this;
        }

        public Builder setStatementType(String statementType) {
            this.statementType = statementType;
            return this;
        }

        public Builder setSqlSource(SqlSource sqlSource) {
            this.sqlSource = sqlSource;
            return this;
        }

        public MappedStatement build() {
            return new MappedStatement(this);
        }


    }


}
