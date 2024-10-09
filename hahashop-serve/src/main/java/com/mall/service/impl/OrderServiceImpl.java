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
    public Boolean addOrder(Order order){
        try {
            // 调用 Dao 插入操作，返回受影响的行数
            order.setIsConfirmed(false);//订单状态
            Integer rowsAffected = orderDao.addOrder(order);
            // 如果返回值大于0，说明插入成功，返回 true
            if(rowsAffected > 0){
                goodDao.buyerNumUpdate(order.getGoodId());
            }
            return true;
        } catch (Exception e) {
            // 捕获异常并记录日志，可以使用 Logger 代替 e.printStackTrace()
            e.printStackTrace();
            return false; // 出现异常时返回 false
        }
    }

    @Override
    public List<Order> getOrdersById(Integer goodId){
        return orderDao.getOrdersById(goodId);
    }

    @Override
    public Boolean confirmOrder(Integer orderId){
        try{
            Integer rowsAffected = orderDao.confirmOrder(orderId);
            if(rowsAffected > 0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean cancelOrder(Integer orderId){
        try{
            Integer rowsAffected = orderDao.cancelOrder(orderId);
            if(rowsAffected > 0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
