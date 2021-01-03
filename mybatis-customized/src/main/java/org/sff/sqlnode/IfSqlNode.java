package org.sff.sqlnode;

import org.sff.utils.OgnlUtils;

public class IfSqlNode implements SqlNode {

    /**
     * 获取表达式，即： <if test="null != orderStatus"> 中的 null != orderStatus
     */
    private String test;

    private SqlNode rootSqlNode;

    public IfSqlNode(String test, MixedSqlNode rootSqlNode) {
        this.test = test;
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public void apply(DynamicContext context) {
        /**
         * 1.通过 ognl 表达式判断 null != orderStatus 的结果值
         * 2.如果test标签属性中的表达式判断为true，才进行子节点的处理
         */
        boolean b = OgnlUtils.evaluateBoolean(test, context.getSqlParameters().get("sqlParameters"));
        if (b) {
            rootSqlNode.apply(context);
        }

    }
}
