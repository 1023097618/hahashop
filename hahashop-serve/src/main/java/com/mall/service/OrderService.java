package com.mall.service;

import com.mall.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {
    Boolean addOrder(Order order);//添加订单
    List<Order> getOrdersByExample(Integer pageSize, Integer pageNum, Integer userId, Integer goodId);//获取所有的订单
    Integer countOrdersByExample(Integer userId, Integer goodId);
    Boolean orderStateChange(Integer orderId, Integer orderState);
    Boolean findOrderByorderId(Integer orderId);
}
