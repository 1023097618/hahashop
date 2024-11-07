package com.mall.dao;

import com.mall.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {
    Integer addOrder(Order order);//添加订单，提数据库改变行数
    List<Order> getOrdersByExample(@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum,
                                  @Param("userId") Integer userId, @Param("goodId") Integer goodId);//获取订单，根据商品ID获取所有的订单
    Integer countOrdersByExample(@Param("userId") Integer userId, @Param("goodId") Integer goodId);

    Integer orderStateChange(@Param("orderId") Integer orderId, @Param("orderState") Integer orderState);
    Integer findOrderByorderId(@Param("orderId") Integer orderId);

}
