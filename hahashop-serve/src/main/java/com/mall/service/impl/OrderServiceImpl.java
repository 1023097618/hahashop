package com.mall.service.impl;

import com.mall.dao.GoodDao;
import com.mall.dao.OrderDao;
import com.mall.entity.Order;
import com.mall.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private GoodDao goodDao;

    @Override
    public Boolean addOrder(Order order){//设计很差，需要修改
        try {
            // 调用 Dao 插入操作，返回受影响的行数
            order.setOrderState(0);//订单状态,0进行中,1已完成,2已取消
            Integer rowsAffected = goodDao.goodNumChange(order.getGoodId(), order.getBuyerGoodsNum());
            if(rowsAffected > 0) {//库存检查，库存够就add订单
                orderDao.addOrder(order);
                goodDao.buyerNumUpdate(order.getGoodId());
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            // 捕获异常并记录日志，可以使用 Logger 代替 e.printStackTrace()
            e.printStackTrace();
            return false; // 出现异常时返回 false
        }
    }

    @Override
    public List<Order> getOrdersByExample(Integer pageSize, Integer pageNum,
                                         Integer userId, Integer goodId) {
        return orderDao.getOrdersByExample(pageSize, pageNum, userId, goodId);
    }

    @Override
    public Integer countOrdersByExample(Integer userId, Integer goodId) { return orderDao.countOrdersByExample(userId, goodId); }


    @Override
    public Boolean orderStateChange(Integer orderId, Integer orderState) { return orderDao.orderStateChange(orderId, orderState) > 0; }
}
