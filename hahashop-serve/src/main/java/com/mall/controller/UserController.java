package com.mall.controller;

import com.mall.common.*;
import com.mall.entity.Good;
import com.mall.entity.Order;
import com.mall.entity.User;
import com.mall.service.AuthService;
import com.mall.service.GoodService;
import com.mall.service.OrderService;
import com.mall.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private TransformUtil transformUtil;
    @Resource
    private UserService userService;
    @Resource
    private OrderService orderService;
    @Resource
    private GoodService goodService;
    @Resource
    private AuthService authService;
    @Autowired
    private CheckUtil checkUtil;

    @RequestMapping("/list")
    public Result<Object> getAllUsers(@RequestParam(required = false, defaultValue = "-1") Integer pageSize, @RequestParam(required = false, defaultValue = "-1") Integer pageNum) {

        if(checkUtil.tookenCheck().getPrivilege() != 1){ return ResultUtil.error(ResultEnum.ILLEGAL_TOKEN); }
        if(pageSize < 0|| pageNum < 0){ return ResultUtil.error(ResultEnum.UNKNOWN_ERROR); }
        List<User> userList = userService.getUsers(pageSize, pageNum);
        List<Map<String, Object>> users = new ArrayList<>();
        for (User user : userList) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", user.getUserId());
            map.put("userPhone", user.getUserPhone());
            map.put("userAddress", user.getUserAddress());
            map.put("userRealName", user.getUserRealName());
            users.add(map);
        }
        Integer totalUsers = userService.countUsers();
        Map<String, Object> data = new HashMap<>();
        data.put("users", users);
        data.put("totalUsers", totalUsers);
        return ResultUtil.success(ResultEnum.SUCCESS, data);
    }

    @RequestMapping("/orderlist")
    public Result<Object> getOrderListByUserId(@RequestParam(required = false, defaultValue = "-1") Integer pageSize,
                                               @RequestParam(required = false, defaultValue = "-1") Integer pageNum,
                                               @RequestParam(required = false) Integer userId) {
        User user = checkUtil.tookenCheck();
        if(user.getPrivilege() != 1){ return ResultUtil.error(ResultEnum.ILLEGAL_TOKEN); }
        List<Order> orderList = orderService.getOrdersByExample(pageSize, pageNum, userId, -1);
        List<Map<String, Object>> orders = new ArrayList<>();
        for(Order order : orderList){
            Map<String, Object> map = new HashMap<>();
            Good good = goodService.getGoodById(order.getGoodId());
            map.put("orderId", order.getOrderId());
            map.put("orderState", order.getOrderState());
            if(good == null){
                map.put("goodImage", null);
                map.put("goodName", "该商品已被下架");
                map.put("goodId", -1);
            }else{
                map.put("goodImage", transformUtil.stringToStringArray(good.getGoodImage())[0]);
                map.put("goodName", good.getGoodName());
                map.put("goodId", order.getGoodId());
            }
            map.put("orderPrice", order.getOrderPrice());
            map.put("buyerDesc", order.getBuyerDesc());
            map.put("buyerRealName", order.getBuyerRealName());
            map.put("buyerAddress", order.getBuyerAddress());
            map.put("buyerPhone", order.getBuyerPhone());
            map.put("buyerGoodsNum", order.getBuyerGoodsNum());
            orders.add(map);
        }
        Integer totalOrders = orderService.countOrdersByExample(userId, -1);
        Map<String, Object> data = new HashMap<>();
        data.put("orders", orders);
        data.put("totalOrders", totalOrders);
        return ResultUtil.success(ResultEnum.SUCCESS, data);
    }
}
