package org.sff.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.sff.bean.Order;
import org.sff.dao.OrderDao;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MyBatisTest {


    public static void main(String[] args) throws Exception {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = factory.openSession();


        Order param = new Order();
        param.setId(1L);
        param.setOrderStatus(0);

        Order order = sqlSession.selectOne("findOrder", param);

        System.out.println(order);

    }

}
