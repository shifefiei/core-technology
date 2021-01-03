package org.sff.config;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

    /**
     * 数据源连接信息
     */
    private DataSource dataSource;

    /**
     * mapper.xml文件中一个select insert update delete标签对应一个 MappedStatement对象
     * key：select insert update delete 就是改标记的id
     */
    private Map<String, MappedStatement> mappedStatementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(String statementId, MappedStatement mappedStatement) {
        mappedStatementMap.put(statementId, mappedStatement);
    }

}
