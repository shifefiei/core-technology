package org.sff.config;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.sff.sqlnode.MixedSqlNode;
import org.sff.sqlnode.SqlNode;
import org.sff.sqlnode.TextSqlNode;
import org.sff.sqlsource.DynamicSqlSource;
import org.sff.sqlsource.RawSqlSource;
import org.sff.sqlsource.SqlSource;
import org.sff.sqlsource.StaticTextSqlNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析 mapper.xml 的sql脚本
 */
public class XMLSqlScriptParser {

    /**
     * 用于判断是否是动态sql脚本
     */
    private boolean isDynamic = false;

    public XMLSqlScriptParser() {
    }

    /**
     * select id as id,order_no as orderNo,order_status as orderStatus,product_name as productName
     * from product_order.order where id = #{id}
     * <if test="null != orderStatus">
     * and order_status = 0
     * </if>
     * <p>
     * 根据 sql 脚本构建 SqlSource，其实就是对select等CRUD标签中的sql脚本进行处理
     * 思路：
     * 1.将 sql 脚本按照不同的类型封装到不同的 SqlNode 中
     * 2.再将 SqlNode 集合封装到 SqlSource 中
     */
    public SqlSource parseSqlScript(Element element) {
        //创建一个 SqlNode 对象出来，所以此时需要一个SqlNode 接口,并且将所产生的 SqlNode 对象存放到 MixedSqlNode 中
        MixedSqlNode mixedSqlNode = this.parseDynamicTags(element);
        /**
         * 因为 sql 脚本有动态脚本和文件脚本，所以 SqlSource 也是分别不同的对象来存储不同类型的脚本
         */
        SqlSource sqlSource = null;
        if (isDynamic) {
            sqlSource = new DynamicSqlSource(mixedSqlNode);
        } else {
            sqlSource = new RawSqlSource(mixedSqlNode);
        }


        return null;
    }

    /**
     * @param element mapper.xml 文件中所有的 <select></select> 标签
     */
    private MixedSqlNode parseDynamicTags(Element element) {
        List<SqlNode> sqlNodes = new ArrayList<>();
        int count = element.nodeCount();
        for (int i = 0; i < count; i++) {
            Node node = element.node(i);
            //拿到了一个select标签，如果该标签是文本类型则封装到 TextSqlNode(该SqlNode中包含 ${}) 或者 StaticTextSqlNode(该SqlNode中不包含${}，可以包含#{})
            if (node instanceof Text) {
                String sqlText = node.getText().trim();
                if (StringUtils.isEmpty(sqlText)) {
                    continue;
                }
                TextSqlNode textSqlNode = new TextSqlNode(sqlText);
                //判断文本中是否有 ${} 标签
                if (textSqlNode.isDynamic()) {
                    sqlNodes.add(textSqlNode);
                    isDynamic = Boolean.TRUE;
                } else {
                    //说明文本中可能有 #{}
                    sqlNodes.add(new StaticTextSqlNode(sqlText));
                }

            }

            if (node instanceof Element) {
                //此时说明 select 标签内部还有子标签，比如动态sql时的 <if>,<where>,<foreach>标签
                //<if>,<where>,<foreach> 标签的解析分别在 IfSqlNode、WhereSqlNode、ForeachSqlNode 中处理

            }
        }
        return new MixedSqlNode(sqlNodes);
    }
}
