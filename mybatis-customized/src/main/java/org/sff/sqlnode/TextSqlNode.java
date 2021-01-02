package org.sff.sqlnode;

public class TextSqlNode implements SqlNode {

    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext context) {

    }

    public boolean isDynamic() {
        if (sqlText.indexOf("${") > -1) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
