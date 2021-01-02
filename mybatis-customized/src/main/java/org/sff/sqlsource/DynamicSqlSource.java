package org.sff.sqlsource;

import org.sff.sqlnode.MixedSqlNode;
import org.sff.sqlnode.SqlNode;

public class DynamicSqlSource implements SqlSource {

    private SqlNode sqlNode;

    public DynamicSqlSource(MixedSqlNode sqlNode) {
        this.sqlNode = sqlNode;
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return null;
    }
}
