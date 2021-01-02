package org.sff.sqlsource;


import java.util.ArrayList;
import java.util.List;

/**
 * 封装完全解析好的能够被执行的 sql 语句 和 解析出来的参数信息集合
 */
public class BoundSql {

    private String sql;

    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    public BoundSql(String sql, List<ParameterMapping> parameterMappingList) {
        this.sql = sql;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
