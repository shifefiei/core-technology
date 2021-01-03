package org.sff.config;

import org.sff.sqlsource.SqlSource;

public class MappedStatement {

    private SqlSource sqlSource;

    /**
     * <select></select> 标签属性 parameterType 对应的类型
     */
    private Class<?> parameterTypeClass;

    /**
     * <select></select> 标签属性 id 值
     */
    private String statementId;

    /**
     * <select></select> 标签属性 statementType 值
     */
    private String statementType;

    /**
     * <select></select> 标签属性 resultType 对应的类型
     */
    private Class<?> resultTypeClass;

    public MappedStatement(SqlSource sqlSource, Class<?> parameterTypeClass, String statementId, String statementType, Class<?> resultTypeClass) {
        this.sqlSource = sqlSource;
        this.parameterTypeClass = parameterTypeClass;
        this.statementId = statementId;
        this.statementType = statementType;
        this.resultTypeClass = resultTypeClass;
    }

    public Class<?> getParameterTypeClass() {
        return parameterTypeClass;
    }

    public void setParameterTypeClass(Class<?> parameterTypeClass) {
        this.parameterTypeClass = parameterTypeClass;
    }

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public Class<?> getResultTypeClass() {
        return resultTypeClass;
    }

    public void setResultTypeClass(Class<?> resultTypeClass) {
        this.resultTypeClass = resultTypeClass;
    }

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public void setSqlSource(SqlSource sqlSource) {
        this.sqlSource = sqlSource;
    }
}
