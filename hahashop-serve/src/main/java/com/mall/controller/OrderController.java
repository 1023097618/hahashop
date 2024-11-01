package com.mall.controller;

import com.mall.common.*;
import com.mall.entity.Good;
import com.mall.entity.Order;
import com.mall.entity.User;
import com.mall.service.AuthService;
import com.mall.service.GoodService;
import com.mall.service.OrderService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mall.common.ResultEnum.*;
import static com.mall.common.StateEnum.*;


@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private AuthService authService;
    @Resource
    private OrderService orderService;
    @Resource
    private GoodService goodService;
    @Resource
    private CheckUtil checkUtil;
    @Resource
    private TransformUtil transformUtil;
    @Resource
    private StateChangeUtil stateChangeUtil;

    @RequestMapping("/buy")
    public Result<Object> addOrder(@RequestBody Order order) {//添加订单
        User user = checkUtil.tookenCheck();
        if(user.getPrivilege() != 2){ return ResultUtil.error(ILLEGAL_TOKEN);}

        Good good = goodService.getGoodById(order.getGoodId());
        if (good == null) {
            return ResultUtil.error(GOOD_NOT_EXIST);
        }

        order.setOrderPrice(good.getGoodPrice());
        if (!checkUtil.isValidPhoneNumber(order.getBuyerPhone())||order.getBuyerGoodsNum() <= 0) {
            return ResultUtil.error(ILLEGAL_INFO);
        } else if (good.getGoodState() == 3) {
            return ResultUtil.error(GOOD_IS_FROZEN);
        }

        user = authService.login(user.getUsername());
        order.setUserId(user.getUserId());
        if (orderService.addOrder(order)) {
            return ResultUtil.success(SUCCESS, null);
        }
        return ResultUtil.error(UNKNOWN_ERROR);
    }

    @RequestMapping({"/sellerlist","/buyerlist"})
    public Result<Object> getOrderListByExample(@RequestParam(required = false, defaultValue = "-1") Integer pageSize, @RequestParam(required = false, defaultValue = "-1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "-1") Integer userId, @RequestParam(required = false, defaultValue = "-1") Integer goodId) {//按商品id获得对应订单
        if(checkUtil.tookenCheck().getPrivilege() != 1 && checkUtil.tookenCheck().getPrivilege() != 2){ return ResultUtil.error(ILLEGAL_TOKEN);}
        User user = checkUtil.tookenCheck();
        if(user.getPrivilege() == 1){
            List<Order> allOrder = orderService.getOrdersByExample(pageSize, pageNum, userId, goodId);
            List<Map<String, Object>> orders = new ArrayList<>();
            for(Order order : allOrder){
                Map<String,Object> o = new HashMap<>();
                o.put("buyerPhone",order.getBuyerPhone());
                o.put("buyerAddress",order.getBuyerAddress());
                o.put("buyerDesc",order.getBuyerDesc());
                o.put("orderId",order.getOrderId());
                o.put("buyerRealName",order.getBuyerRealName());
                o.put("orderState",order.getOrderState());
                o.put("buyerGoodsNum",order.getBuyerGoodsNum());
                orders.add(o);
            }
            Integer totalOrders = orderService.countOrdersByExample(userId, goodId);
            Map<String,Object> data = new HashMap<>();
            data.put("orders",orders);
            data.put("totalOrders",totalOrders);
            return ResultUtil.success(SUCCESS,data);
        }else if(user.getPrivilege() == 2){
            List<Order> allOrder = orderService.getOrdersByExample(pageSize, pageNum, user.getUserId(), goodId);
            List<Map<String, Object>> orders = new ArrayList<>();
            for(Order order : allOrder){
                Good good = goodService.getGoodById(order.getGoodId());
                Map<String,Object> o = new HashMap<>();
                o.put("buyerPhone",order.getBuyerPhone());
                o.put("buyerAddress",order.getBuyerAddress());
                o.put("buyerDesc",order.getBuyerDesc());
                o.put("orderId",order.getOrderId());
                o.put("buyerRealName",order.getBuyerRealName());
                o.put("goodId",order.getGoodId());
                o.put("goodImage",transformUtil.stringToStringArray(good.getGoodImage())[0]);
                o.put("goodName",good.getGoodName());
                o.put("goodPrice",good.getGoodPrice());
                o.put("orderState",order.getOrderState());
                o.put("buyerGoodsNum",order.getBuyerGoodsNum());
                orders.add(o);
            }
            Integer totalOrders = orderService.countOrdersByExample(user.getUserId(), goodId);
            Map<String,Object> data = new HashMap<>();
            data.put("orders",orders);
            data.put("totalOrders",totalOrders);
            return ResultUtil.success(SUCCESS,data);
        }else {
            return ResultUtil.error(ILLEGAL_INFO);
        }
    }

    @RequestMapping("/confirmsell")
    public Result<Object> completeOrder(@RequestBody Map<String, Object> orderRequest) {
        User user = checkUtil.tookenCheck();
        if(user.getPrivilege() != 1){ return ResultUtil.error(ILLEGAL_TOKEN);}
        if(orderService.orderStateChange((Integer) orderRequest.get("orderId"), stateChangeUtil.StateChange(COMPLETE))){
            return ResultUtil.success(SUCCESS, null);
        }else{
            return ResultUtil.error(UNKNOWN_ERROR);
        }
    }

    @RequestMapping("cancelsell")
    public Result<Object> cancelOrder(@RequestBody Map<String, Object> orderRequest) {
        User user = checkUtil.tookenCheck();
        if(user.getPrivilege() != 1){ return ResultUtil.error(ILLEGAL_TOKEN);}
        if(orderService.orderStateChange((Integer) orderRequest.get("orderId"), stateChangeUtil.StateChange(CANCELED))){
            return ResultUtil.success(SUCCESS, null);
        }else{
            return ResultUtil.error(UNKNOWN_ERROR);
        }
    }
}
