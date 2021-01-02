package org.sff.test;

import org.dom4j.Document;
import org.sff.config.Configuration;
import org.sff.config.MyResources;
import org.sff.config.XMLConfigParser;
import org.sff.utils.DocumentUtils;

import java.io.InputStream;

public class CustomizedMyBatisTest {


    public static void main(String[] args) throws Exception {

        String resource = "mybatis-config.xml";
        InputStream inputStream = MyResources.getResourceAsStream(resource);
        Document document = DocumentUtils.readDocument(inputStream);

        //从Document中解析出Configuration,数据库连接的基本信息以及Mapper文件的信息
        XMLConfigParser parser = new XMLConfigParser();
        Configuration configuration = parser.parser(document.getRootElement());
        System.out.println(configuration);


    }

}
