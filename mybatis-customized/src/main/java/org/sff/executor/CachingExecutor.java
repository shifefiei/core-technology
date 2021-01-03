package org.sff.executor;

import org.sff.config.Configuration;
import org.sff.config.MappedStatement;
import org.sff.sqlsource.SqlSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 二级缓存执行器
 */
public class CachingExecutor implements Executor {

    private Map<String, List<Object>> twoLevelCache = new HashMap<>();

    private Executor executor;

    public CachingExecutor(Executor executor) {
        this.executor = executor;
    }


    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param) {
        /**
         * TODO 这里先处理二级缓存逻辑,先这样简单的表示一下子吧
         * 1. 从二级缓存中根据sql语句获取处理结果。如果有，则直接返回，如果没有则继续委托给基本执行器处理(即一级缓存处理器)
         */
        SqlSource sqlSource = mappedStatement.getSqlSource();
        String sql = sqlSource.getBoundSql(param).getSql();
        List<Object> objects = twoLevelCache.get(sql);
        if (null != objects) {
            return (List<T>) objects;
        }

        //调用后序的一级缓存处理器
        List<Object> queryResult = executor.query(mappedStatement, configuration, param);

        return (List<T>) queryResult;
    }
}
