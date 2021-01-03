package org.sff.sqlsource;

import org.sff.sqlnode.DynamicContext;
import org.sff.sqlnode.MixedSqlNode;

/**
 * RawSqlSource : sql 语句中只存在 #{} 或者可以直接执行的sql语句，封装非动态的SqlNode信息（也就是不带有${}或者动态sql标签的sqlNode）
 */
public class RawSqlSource implements SqlSource {

    private SqlSource sqlSource;

    public RawSqlSource(MixedSqlNode sqlNode) {
        DynamicContext context = new DynamicContext(null);
        sqlNode.apply(context);

        // 在这里要先对sql节点进行解析
        SqlSourceParser sqlSourceParser = new SqlSourceParser();
        sqlSource = sqlSourceParser.parse(context.getSql());
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return sqlSource.getBoundSql(param);
    }
}
