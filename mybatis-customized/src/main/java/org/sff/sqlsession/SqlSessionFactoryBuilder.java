package org.sff.sqlsession;


import org.dom4j.Document;
import org.sff.config.Configuration;
import org.sff.config.XMLConfigParser;
import org.sff.utils.DocumentUtils;

import java.io.InputStream;

/**
 * 使用构建者模式创建一个 SqlSessionFactory
 */
public class SqlSessionFactoryBuilder {

    public static SqlSessionFactory create(InputStream inputStream) throws Exception {
        //读取mybatis的所有配置信息
        Document document = DocumentUtils.readDocument(inputStream);
        XMLConfigParser parser = new XMLConfigParser();
        Configuration configuration = parser.parser(document.getRootElement());
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return sqlSessionFactory;
    }
}
