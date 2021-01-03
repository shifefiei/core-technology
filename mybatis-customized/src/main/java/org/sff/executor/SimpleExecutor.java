package org.sff.executor;

import org.sff.config.Configuration;
import org.sff.config.MappedStatement;
import org.sff.sqlsource.BoundSql;
import org.sff.sqlsource.ParameterMapping;
import org.sff.sqlsource.SqlSource;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class SimpleExecutor extends BaseExecutor {

    @Override
    protected List<Object> queryFromDataBase(MappedStatement mappedStatement, Configuration configuration, Object param) {

        List<Object> results = new ArrayList<Object>();

        /**
         * 真正操作jdbc的逻辑
         */
        try {
            Connection connection = this.getConnection(configuration);
            BoundSql boundSql = this.getBoundSql(mappedStatement.getSqlSource(), param);
            String statementType = mappedStatement.getStatementType();
            if (statementType.equals("PREPARED")) {
                //创建 statement
                PreparedStatement statement = connection.prepareStatement(boundSql.getSql());
                //设置sql参数
                this.handleParameter(statement, boundSql, param);

                ResultSet resultSet = statement.executeQuery();

                //处理查询结果
                this.handleResult(resultSet, mappedStatement, results);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    private void handleResult(ResultSet resultSet, MappedStatement mappedStatement, List<Object> results) throws Exception {
        Class<?> resultTypeClass = mappedStatement.getResultTypeClass();

        Object resultTypeObject = resultTypeClass.newInstance();  //sql执行后要返回的结果

        while (resultSet.next()) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnName = metaData.getColumnLabel(i + 1);
                Field field = resultTypeClass.getDeclaredField(columnName);
                field.setAccessible(Boolean.TRUE);

                String colValue = resultSet.getString(columnName);
                if (field.getType() == Long.class) {
                    field.set(resultTypeObject, Long.parseLong(colValue));

                } else if (field.getType() == Integer.class) {
                    field.set(resultTypeObject, Integer.parseInt(colValue));
                } else {
                    field.set(resultTypeObject, colValue);
                }
            }

            results.add(resultTypeObject);
        }
    }

    /**
     * @param param sql 语句中的入参,上层API调用传递过来的
     */
    private void handleParameter(PreparedStatement statement, BoundSql boundSql, Object param) throws Exception {
        if (param instanceof Integer) {
            statement.setObject(1, Integer.parseInt(param.toString()));
        } else if (param instanceof String) {
            statement.setObject(1, param.toString());
        } else {

            List<ParameterMapping> parameterMappings = boundSql.getParameterMappingList();//对 #{} 处理之后得到的参数集合
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameter = parameterMappings.get(i);
                // #{}中的参数名称，也应该和POJO类型中的属性名称相同
                String name = parameter.getName();

                // 使用反射获取指定 name 的值
                Class<?> clazz = param.getClass();
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                Object nameValue = field.get(param);

                statement.setObject(i + 1, nameValue);
            }
        }
    }

    private BoundSql getBoundSql(SqlSource sqlSource, Object param) {
        BoundSql boundSql = sqlSource.getBoundSql(param);
        return boundSql;
    }

    private Connection getConnection(Configuration configuration) throws Exception {
        DataSource dataSource = configuration.getDataSource();
        return dataSource.getConnection();
    }


}
