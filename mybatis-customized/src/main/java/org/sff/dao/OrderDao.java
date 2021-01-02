package org.sff.dao;

import org.sff.bean.Order;

import java.util.Map;

public interface OrderDao {

    Order findOrder(Map<String, Object> map);

}
