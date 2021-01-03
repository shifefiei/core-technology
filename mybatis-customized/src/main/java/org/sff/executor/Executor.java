package org.sff.executor;

import org.sff.config.Configuration;
import org.sff.config.MappedStatement;

import java.util.List;


/**
 * 执行器接口
 */
public interface Executor {

    /**
     * @param mappedStatement 获取sql语句和入参出参等信息
     * @param configuration   获取数据源对象
     * @param param           入参对象
     */
    <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param);

}
