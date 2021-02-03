package org.sff.utils;


import bean.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 原始 jdbc 连接写法
 */
public class JdbcUtils {

    private static Connection connection = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://113.133.166.59:3306/product_order", "root", "TYY@shi@fei6");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入一条记录
     */
    public static void insert(Order order) throws Exception {
        String sql = "insert into product_order.order(order_no,order_status,product_name) values (?,?,?) ";
        //预处理对象
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, order.getOrderNo());
            statement.setString(2, String.valueOf(order.getOrderStatus()));
            statement.setString(3, order.getProductName());

            int i = statement.executeUpdate();
            System.out.println("插入结果:" + i);
        } finally {
            close();
        }
    }

    /**
     * 根据ID查询
     */
    public static void selectById(Long id) throws Exception {

        String sql = "select * from product_order.order where id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + " " + resultSet.getString("order_no") + " " + resultSet.getString("product_name"));
            }
        } finally {
            close();
        }
    }

    public static void close() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {

        selectById(1L);
    }

}
