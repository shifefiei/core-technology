package org.sff.sqlnode;

import java.util.ArrayList;
import java.util.List;

public class MixedSqlNode implements SqlNode {

    private List<SqlNode> sqlNodes = new ArrayList<>();

    public MixedSqlNode(List<SqlNode> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }

    /**
     * 遍历 sqlNode 集合，拼接所有的 sql ，最终生成的 sql 都存储到 DynamicContext 中
     */
    @Override
    public void apply(DynamicContext context) {
        for (SqlNode sqlNode : sqlNodes) {
            sqlNode.apply(context);
        }
    }
}
