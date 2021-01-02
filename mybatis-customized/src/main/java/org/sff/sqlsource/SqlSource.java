package org.sff.sqlsource;

/**
 * 获取被JDBC程序直接执行的Sql语句
 */
public interface SqlSource {


    BoundSql getBoundSql(Object param);

}
