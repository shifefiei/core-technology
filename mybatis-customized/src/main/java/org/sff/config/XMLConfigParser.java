package org.sff.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.sff.utils.DocumentUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class XMLConfigParser {

    private MyConfiguration configuration;

    public XMLConfigParser() {
        this.configuration = new MyConfiguration();
    }

    /**
     * 解析 mybatis-config.xml 文件
     */
    public MyConfiguration parser(Element rootElement) throws Exception {
        /**
         * 1.解析 <environments> 元素
         */
        this.parseEnvironments(rootElement.element("environments"));

        /**
         * 2.解析 <mappers> 元素
         */
        this.parseMappers(rootElement.element("mappers"));

        return configuration;
    }

    private void parseEnvironments(Element rootElement) throws Exception {
        String defaultValue = rootElement.attributeValue("default");
        if (StringUtils.isEmpty(defaultValue)) {
            return;
        }
        List<Element> environment = rootElement.elements("environment");
        for (Element element : environment) {
            this.parseEnvironment(element);
        }
    }

    /**
     * 解析 <environment></environment>
     */
    private void parseEnvironment(Element element) {
        Element dataSourceElement = element.element("dataSource");
        Properties properties = this.getProperties(dataSourceElement);

        String driver = (String) properties.get("driver");
        String url = (String) properties.get("url");
        String username = (String) properties.get("username");
        String password = (String) properties.get("password");
        DataSource dataSource = new UnpooledDataSource(driver, url, username, password);
        configuration.setDataSource(dataSource);
    }

    private Properties getProperties(Element dataSourceElement) {
        Properties properties = new Properties();
        List<Element> propertys = dataSourceElement.elements("property");
        for (Element element : propertys) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name, value);
        }
        return properties;
    }

    private void parseMappers(Element rootElement) throws Exception {
        List<Element> mapper = rootElement.elements("mapper");
        for (Element element : mapper) {
            this.parseMapper(element);
        }
    }

    private void parseMapper(Element element) throws Exception {
        String resource = element.attributeValue("resource");
        /**
         * 处理每一个 mapper.xml 的解析，即重新获取 Document 对象
         */
        InputStream inputStream = MyResources.getResourceAsStream(resource);
        Document mapperDocument = DocumentUtils.readDocument(inputStream);

        XMLMapperParser mapperParser = new XMLMapperParser(configuration);
        mapperParser.parse(mapperDocument.getRootElement());
    }
}
