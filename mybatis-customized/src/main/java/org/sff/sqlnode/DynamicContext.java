package org.sff.sqlnode;


import java.util.HashMap;
import java.util.Map;

/**
 * 封装了 SqlNode 解析过程中需要的入参，存储 SqlNode 解析mapper.xml 后产生的sql片信
 */
public class DynamicContext {
    /**
     * 用于存储拼接后的 sql 语句
     */
    private StringBuilder sb = new StringBuilder();

    /**
     * 存在 SqlNode 解析需要的入参
     */
    private Map<String, Object> sqlParameters = new HashMap<>();

    public DynamicContext(Object param) {
        //传递入参
        sqlParameters.put("sqlParameters", param);
    }

    public void appendSql(String sql) {
        sb.append(sql).append(" ");
    }

    public String getSql() {
        return sb.toString();
    }

    public Map<String, Object> getSqlParameters() {
        return sqlParameters;
    }

    public void setSqlParameters(Map<String, Object> sqlParameters) {
        this.sqlParameters = sqlParameters;
    }
}
