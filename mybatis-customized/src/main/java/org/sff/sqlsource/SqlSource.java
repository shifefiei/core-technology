package org.sff.sqlsource;

/**
 * 获取被JDBC程序直接执行的Sql语句
 * <p>
 * 1.DynamicSqlSource : sql 语句中存在 ${} 和 动态标签
 * 2.RawSqlSource : sql 语句中只存在 #{} 或者可以直接执行的sql语句
 * 3.StaticSqlSource : 封装了经过 DynamicSqlSource 和 RawSqlSource 封装之后的sql信息
 */
public interface SqlSource {


    BoundSql getBoundSql(Object param);

}
