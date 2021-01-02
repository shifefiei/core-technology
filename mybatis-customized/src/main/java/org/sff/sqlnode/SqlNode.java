package org.sff.sqlnode;

/**
 * 定义脚本解析功能，具体实现有：
 * 1.TextSqlNode ，用于封装文本sql，该sql中包含 ${}，整个 TextSqlNode 存储到 DynamicSqlSource 中
 * 2.StaticTextSqlNode，用于封装文本sql，该sql中不包含${}，可以包含#{}，整个 StaticTextSqlNode 存储到RawSqlSource中
 * 3.以下SqlNode都存储到 DynamicSqlSource 中
 * (1)IfSqlNode
 * (2)WhereSqlNode
 * (3)ForeachSqlNode
 * (4)MixedSqlNode : 以集合的方式存储 IfSqlNode、WhereSqlNode、ForeacheSqlNode
 */
public interface SqlNode {

    /**
     * 构建一个 SqlNode 需要的参数信息，脚本信息都要封装到 DynamicContext 中
     * @param context
     */
    void apply(DynamicContext context);

}
