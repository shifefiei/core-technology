package org.sff.sqlsource;

import org.sff.sqlnode.DynamicContext;
import org.sff.sqlnode.SqlNode;

public class StaticTextSqlNode implements SqlNode {
    private String sqlText;

    public StaticTextSqlNode(String sqlText) {
        super();
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext context) {
        // sql文本追加
        context.appendSql(sqlText);
    }

}
