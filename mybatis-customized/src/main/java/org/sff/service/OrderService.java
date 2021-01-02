package org.sff.service;

import org.sff.bean.Order;

public interface OrderService {

    Order findOrder(String orderNo, String productName);
}
