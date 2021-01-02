package org.sff.sqlsource;

import org.sff.sqlnode.MixedSqlNode;
import org.sff.sqlnode.SqlNode;

public class RawSqlSource implements SqlSource {

    private SqlNode sqlNode;

    public RawSqlSource(MixedSqlNode sqlNode) {
        this.sqlNode = sqlNode;
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return null;
    }
}
