package org.sff.sqlsession;

import org.sff.config.Configuration;
import org.sff.config.MappedStatement;
import org.sff.executor.CachingExecutor;
import org.sff.executor.SimpleExecutor;
import org.sff.sqlsource.SqlSource;

import java.util.List;
import java.util.Map;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statementId, Object param) {
        List<Object> list = this.selectList(statementId, param);
        if (list == null || list.size() == 0) {
            return null;
        } else if (list.size() == 1) {
            return (T) list.get(0);
        } else {
            throw new RuntimeException("只能返回一个对象");
        }
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        //mapper.xml文件中一个select  insert    update  delete标签对应一个 MappedStatement对象
        Map<String, MappedStatement> mappedStatementMap = configuration.getMappedStatementMap();
        /**
         * 1.执行Statement的操作，执行方式有多种
         *      (1)带有二级缓存的执行方式
         *      (2)基本执行方式，分为 基本执行器、批处理执行器 等，他们 都是只带有一级缓存
         *
         * 2.利用 MappedStatement对象，可以根据它是否否配置了二级缓存来确定执行那个 Executor
         */

        MappedStatement statement = mappedStatementMap.get(statementId);

        CachingExecutor cachingExecutor = new CachingExecutor(new SimpleExecutor());
        List<Object> query = cachingExecutor.query(statement, configuration, param);

        return (List<T>) query;
    }
}
