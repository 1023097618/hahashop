package com.mall.controller;

import com.mall.common.InputCheckUtil;
import com.mall.common.Result;
import com.mall.common.ResultUtil;
import com.mall.common.StateChangeUtil;
import com.mall.entity.Good;
import com.mall.entity.Order;
import com.mall.service.GoodService;
import com.mall.service.OrderService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mall.common.ResultEnum.*;
import static com.mall.common.StateEnum.ACTIVE;
import static com.mall.common.StateEnum.FROZEN;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private GoodService goodService;

    @RequestMapping("/buy")
    public Result<Object> addOrder(@RequestBody Order order) {//添加订单
        Good good = goodService.getGoodById(order.getGoodId());
        if(good==null){
            return ResultUtil.error(GOOD_NOT_EXIST);
        }
        else if(!InputCheckUtil.isValidPhoneNumber(order.getBuyerPhone())) {
            return  ResultUtil.error(ILLEGAL_INFO);
        }else if(good.getGoodState()== null ||good.getGoodState() == 1) {
            return ResultUtil.error(GOOD_IS_FROZEN);
        }else if(orderService.addOrder(order)){
            return ResultUtil.success(SUCCESS,null);
        }else{
            return ResultUtil.error(UNKNOWN_ERROR);
        }
    }

    @RequestMapping("/list")
    public Result<Object> getOrderList(HttpServletRequest request, @RequestParam Integer goodId) {//按商品id获得对应订单
        // 获取所有 Cookie
        Boolean isTokenValid = (Boolean) request.getAttribute("isTokenValid");

        if(isTokenValid!=null&&isTokenValid) {
            if(goodService.getGoodById(goodId) == null){
                return ResultUtil.error(GOOD_NOT_EXIST);
            }else{
                Integer goodState = goodService.getGoodById(goodId).getGoodState();
                Map<String, Object> data = new HashMap<>();
                data.put("goodState",goodState);
                List<Order> orders = orderService.getOrdersById(goodId);
                if(goodState!=null && goodState == 1){
                    orders.sort((o1, o2) -> {
                        if (o1.getIsConfirmed() && !o2.getIsConfirmed()) {
                            return -1; // o1 放在前面
                        } else if (!o1.getIsConfirmed() && o2.getIsConfirmed()) {
                            return 1;  // o2 放在前面
                        }
                        return 0; // 保持原顺序
                    });
                }
                data.put("orders",orders);
                return ResultUtil.success(SUCCESS,data);
            }
        }else{
            return ResultUtil.error(ILLEGAL_TOKEN);
        }
    }

    @RequestMapping("/sell")
    public Result<Object> sellOrder(HttpServletRequest request,@RequestBody Map<String, Object> orderRequest) {
        Integer orderId = Integer.valueOf(orderRequest.get("orderId").toString());
        Integer goodId = Integer.valueOf(orderRequest.get("goodId").toString());
        if(goodService.getGoodById(goodId).getGoodState() == 1) {//已经被冻结
            return ResultUtil.error(GOOD_IS_FROZEN);
        }
        Boolean isTokenValid = (Boolean) request.getAttribute("isTokenValid");
        if (isTokenValid != null&&isTokenValid) {
            if(orderService.confirmOrder(orderId)){
                Integer goodState = StateChangeUtil.StateChange(FROZEN);
                if(goodService.stateChange(goodId, goodState))
                {
                    return ResultUtil.success(SUCCESS,null);
                }else {
                    return ResultUtil.error(UNKNOWN_ERROR);
                }
            }
        }else {
            return ResultUtil.error(ILLEGAL_TOKEN);
        }
        return ResultUtil.error(UNKNOWN_ERROR);
    }

    @RequestMapping("cancelsell")
    public Result<Object> cancelsellOrder(@RequestBody Map<String, Object> orderRequest,HttpServletRequest request) {
        Integer orderId = Integer.valueOf(orderRequest.get("orderId").toString());
        Integer goodId = Integer.valueOf(orderRequest.get("goodId").toString());
        Boolean isTokenValid = (Boolean) request.getAttribute("isTokenValid");
        if (isTokenValid != null&&isTokenValid) {
            if(orderService.cancelOrder(orderId)){
                Integer goodState = StateChangeUtil.StateChange(ACTIVE);
                if(goodService.stateChange(goodId, goodState))
                {
                    return ResultUtil.success(SUCCESS,null);
                }else{
                    return ResultUtil.error(UNKNOWN_ERROR);
                }
            }
        }else{
            return ResultUtil.error(ILLEGAL_TOKEN);
        }
        return ResultUtil.error(UNKNOWN_ERROR);
    }
}
