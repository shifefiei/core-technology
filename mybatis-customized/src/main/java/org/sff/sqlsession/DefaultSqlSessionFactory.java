package org.sff.sqlsession;

import org.sff.config.Configuration;

/**
 * 会话工厂,工厂模式
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    /**
     * 构建会话需要的配置信息，需要注入
     */
    private Configuration configuration;


    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(configuration);
    }
}
