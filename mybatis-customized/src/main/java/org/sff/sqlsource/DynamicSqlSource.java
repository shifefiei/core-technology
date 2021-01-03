package org.sff.sqlsource;

import org.sff.sqlnode.DynamicContext;
import org.sff.sqlnode.MixedSqlNode;
import org.sff.sqlnode.SqlNode;

/**
 * 专门封装和处理带有${}和动态sql标签的sql语句
 */
public class DynamicSqlSource implements SqlSource {

    private SqlNode sqlNode;

    public DynamicSqlSource(MixedSqlNode sqlNode) {
        this.sqlNode = sqlNode;
    }

    @Override
    public BoundSql getBoundSql(Object param) {

        DynamicContext context = new DynamicContext(param);
        sqlNode.apply(context);


        // 利用SqlSourceParser来处理 #{}
        SqlSourceParser sqlSourceParser = new SqlSourceParser();
        SqlSource sqlSource = sqlSourceParser.parse(context.getSql());
        return sqlSource.getBoundSql(param);
    }
}
