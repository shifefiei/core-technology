package org.sff.sqlnode;

import org.sff.utils.GenericTokenParser;
import org.sff.utils.OgnlUtils;
import org.sff.utils.SimpleTypeRegistry;
import org.sff.utils.TokenHandler;

/**
 * TextSqlNode ，用于封装文本sql，该sql中包含 ${}，整个 TextSqlNode 存储到 DynamicSqlSource 中
 */
public class TextSqlNode implements SqlNode {

    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    /**
     * 要处理${} 比如说 username like '%${username}' 入参username="aaa" 处理之后username like '%aaa'
     */
    @Override
    public void apply(DynamicContext context) {
        TokenHandler tokenHandler = new BindingTokenParser(context);
        GenericTokenParser tokenParser = new GenericTokenParser("${", "}", tokenHandler);
        // tokenParser.parse(sqlText)参数是未处理的，返回值是已处理的（没有${}）
        context.appendSql(tokenParser.parse(sqlText));

    }

    public boolean isDynamic() {
        if (sqlText.indexOf("${") > -1) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private static class BindingTokenParser implements TokenHandler {
        private DynamicContext context;

        public BindingTokenParser(DynamicContext context) {
            this.context = context;
        }

        /**
         * expression：比如说${username}，那么 expression 就是 username, username 也就是Ognl表达式
         */
        @Override
        public String handleToken(String expression) {
            Object paramObject = context.getSqlParameters().get("sqlParameters");
            if (paramObject == null) {
                return "";
            } else if (SimpleTypeRegistry.isSimpleType(paramObject.getClass())) {
                return String.valueOf(paramObject);
            }
            // 使用Ognl api去获取相应的值
            Object value = OgnlUtils.getValue(expression, context.getSqlParameters());
            String srtValue = value == null ? "" : String.valueOf(value);
            return srtValue;
        }

    }
}
