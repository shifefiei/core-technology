package org.sff.test;

import org.sff.bean.Order;
import org.sff.config.MyResources;
import org.sff.sqlsession.SqlSession;
import org.sff.sqlsession.SqlSessionFactory;
import org.sff.sqlsession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class CustomizedMyBatisTest {


    public static void main(String[] args) throws Exception {

        String resource = "mybatis-config.xml";
        InputStream inputStream = MyResources.getResourceAsStream(resource);
//        Document document = DocumentUtils.readDocument(inputStream);
//
//        //从Document中解析出Configuration,数据库连接的基本信息以及Mapper文件的信息
//        XMLConfigParser parser = new XMLConfigParser();
//        Configuration configuration = parser.parser(document.getRootElement());
//        System.out.println(configuration);


        SqlSessionFactory factory = SqlSessionFactoryBuilder.create(inputStream);
        SqlSession sqlSession = factory.openSqlSession();

        Order param = new Order();
        param.setId(1L);
        param.setOrderStatus(0);

        Order order = sqlSession.selectOne("findOrder", param);
        System.out.println(order);


    }

}
