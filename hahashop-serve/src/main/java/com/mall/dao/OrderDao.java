package com.mall.dao;

import com.mall.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao {
    Integer addOrder(Order order);//添加订单，提数据库改变行数
    List<Order> getOrdersById(Integer goodId);//获取订单，根据商品ID获取所有的订单
    Integer confirmOrder(Integer orderId);//订单确认，根据订单号，改isConfirmed为true，对应接口【卖商品】
    Integer cancelOrder(Integer orderId);//根据订单号，改isConfirmed为false，对应【撤销订单】
}
