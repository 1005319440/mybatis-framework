package cn.zfy.mybatis.framework.sqlnode;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname DynamicContext
 * @Description sqlNode处理过程中的上下文对象
 * @Date 2021/9/3 11:35
 * @Created by zfy
 */
@NoArgsConstructor
public class DynamicContext {

    /**
     * 为了将所有sqlNode处理之后的信息，拼接成一条完整的sql语句
     */
    private StringBuffer sb = new StringBuffer();

    /**
     * 为了sql执行过程中需要的一些信息
     */
    private Map<String, Object> bindings = new HashMap<>();

    public DynamicContext(Object param) {
        this.bindings.put("_parameter", param);
    }

    public String getSql() {
        return sb.toString();
    }

    public void appendSql(String sqlText) {
        this.sb.append(sqlText).append(" ");
    }

    public Map<String, Object> getBindings() {
        return bindings;
    }

    public void addBinding(String name, Object binding) {
        this.bindings.put(name, binding);
    }

}
