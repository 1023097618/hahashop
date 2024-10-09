package com.mall.service;

import com.mall.entity.Order;

import java.util.List;

public interface OrderService {
    Boolean addOrder(Order order);//添加订单
    List<Order> getOrdersById(Integer id);//获取所有的订单
    Boolean confirmOrder(Integer orderId);
    Boolean cancelOrder(Integer orderId);
}
