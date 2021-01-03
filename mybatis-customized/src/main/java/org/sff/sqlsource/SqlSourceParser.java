package org.sff.sqlsource;

import org.sff.utils.GenericTokenParser;
import org.sff.utils.ParameterMappingTokenHandler;

/**
 * 将 DynamicSqlSource 和 RawSqlSource 解析成 StaticSqlSource
 * StaticSqlSource存储的就是只有 ? 的sql语句以及相应的sql信息
 */
public class SqlSourceParser {

    //处理从 <select></select>标签中解析出来的sql
    public SqlSource parse(String sql) {

        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();

        //处理sql语句中 #{}
        GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        String executeSql = tokenParser.parse(sql);
        return new StaticSqlSource(executeSql,tokenHandler.getParameterMappings());
    }
}
