package org.sff.config;

import org.dom4j.Element;
import org.sff.sqlsource.SqlSource;

import java.util.HashMap;


/**
 * mapper.xml 文件中的 select update  delete  insert 分别对应一个 statement 对象
 */
public class XMLStatementParser {

    private Configuration configuration;

    public XMLStatementParser(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 解析 mapper.xml 文档内容，组装成一个个 MapperStatement 对象
     */
    public void parseStatement(Element element, String namespace) {

        String id = element.attributeValue("id");

        /**
         * <select id="findOrder"
         *             parameterType="java.util.Map"
         *             statementType="PREPARED"
         *             resultType="bean.Order">
         */
        String parameterType = element.attributeValue("parameterType");
        String resultType = element.attributeValue("resultType");

        Class<?> parameterTypeClass = this.resolve(parameterType);  // java.util.Map 的对象类型
        Class<?> resultTypeClass = this.resolve(resultType);  //bean.Order 的对象类型


        String statementType = element.attributeValue("statementType");
        statementType = statementType == null || statementType.equals("") ? "PREPARED" : statementType;

        //element 代表了 select 标签的xml文档
        SqlSource sqlSource = this.createSqlSource(element);

        MappedStatement statement = new MappedStatement(sqlSource, parameterTypeClass, id, statementType, resultTypeClass);
        configuration.setMappedStatementMap(id, statement);
    }

    /**
     * 根据 mapper.xml 文件中的 <select></select> 中的sql构建一个SqlSource
     */
    private SqlSource createSqlSource(Element element) {
        XMLSqlScriptParser scriptParser = new XMLSqlScriptParser();
        SqlSource sqlSource = scriptParser.parseSqlScript(element);
        return sqlSource;
    }

    private Class<?> resolve(String className) {
        try {
            Class<?> aClass = Class.forName(className);
            return aClass;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
