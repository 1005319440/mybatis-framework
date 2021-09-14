package cn.zfy.mybatis.sqlnode;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname DynamicContext
 * @Description sqlNode拼接的时候 动态上下文
 * @Date 2021/9/7 14:13
 * @Created by zfy
 */
@NoArgsConstructor
public class DynamicContext {


    private StringBuffer sb = new StringBuffer();

    private Map<String, Object> bindings = new HashMap<>();

    public DynamicContext(Object param) {
        this.bindings.put("_parameter", param);
    }

    public void appendSql(String sql) {
        sb.append(sql).append(" ");
    }

    public String getSql() {
        return sb.toString();
    }

    public Map<String, Object> getBindings() {
        return bindings;
    }
}
