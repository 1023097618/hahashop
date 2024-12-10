package com.mall.controller;


import com.mall.common.CheckUtil;
import com.mall.common.Result;
import com.mall.common.ResultEnum;
import com.mall.common.ResultUtil;
import com.mall.entity.Cart;
import com.mall.entity.Good;
import com.mall.entity.User;
import com.mall.service.CartService;
import com.mall.service.GoodService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Resource
    private CheckUtil checkUtil;
    @Resource
    private CartService cartService;
    @Resource
    private GoodService goodService;

    @RequestMapping("/changenum")
    public Result<Object> changeNum(@RequestBody Map<String, String> map) {
        User user = checkUtil.tookenCheck();
        if(user == null || user.getPrivilege() != 2){ return ResultUtil.error(ResultEnum.ILLEGAL_TOKEN); }
        Integer goodId = Integer.valueOf(map.get("goodId"));
        try{
            Integer cartGoodNum = Integer.valueOf(map.get("cartGoodNum"));
        }catch (Exception e){
            return ResultUtil.error(ResultEnum.ILLEGAL_INFO);
        }

        Integer cartGoodNum = Integer.valueOf(map.get("cartGoodNum"));
        if(cartGoodNum <= 0){ return ResultUtil.error(ResultEnum.ILLEGAL_INFO); }
        Integer userId = user.getUserId();
        if(cartService.changeCartGoodNum(userId, goodId, cartGoodNum)){
            return ResultUtil.success(ResultEnum.SUCCESS, null);
        }else{
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
        }
    }

    @RequestMapping("/add")
    public Result<Object> addCart(@RequestBody Map<String, String> map) {
        User user = checkUtil.tookenCheck();
        if (user == null||user.getPrivilege()!=2) { return ResultUtil.error(ResultEnum.ILLEGAL_TOKEN); }

        Integer userId = user.getUserId();
        Integer goodId = Integer.valueOf(map.get("goodId"));

        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setGoodId(goodId);
        cart.setCartGoodNum(1);

        if(cartService.cartSelect(userId, goodId)){
           return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);//应该改为物品已存在
        }

        if(cartService.cartAdd(cart)){
            return ResultUtil.success(ResultEnum.SUCCESS, null);
        }else {
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
        }
    }

    @RequestMapping("/list")
    public Result<Object> listCart(@RequestParam Integer pageSize, @RequestParam Integer pageNum) {
        User user = checkUtil.tookenCheck();
        if(user == null){ return ResultUtil.error(ResultEnum.ILLEGAL_TOKEN); }
        Integer userId = user.getUserId();

        List<Cart> carts = cartService.getCartGoods(userId, pageSize, pageNum);
        List<Map<String, Object>> goods = new ArrayList<>();

        for(Cart cart : carts){
            Map<String, Object> thisgood = new HashMap<>();
            Good good = goodService.getGoodById(cart.getGoodId());
            thisgood.put("goodName", good.getGoodName());
            thisgood.put("goodPrice", good.getGoodPrice());
            thisgood.put("goodNum", good.getGoodNum());
            thisgood.put("goodId", cart.getGoodId());
            thisgood.put("goodImage", good.getGoodImage());
            thisgood.put("cartGoodNum", cart.getCartGoodNum());
            goods.add(thisgood);
        }

        Integer totalGoods = cartService.countCart(userId);

        Map<String, Object> data = new HashMap<>();
        data.put("totalGoods", totalGoods);
        data.put("goods", goods);
        return ResultUtil.success(ResultEnum.SUCCESS, data);
    }

    @RequestMapping("/delete")
    public Result<Object> cartDelete(@RequestBody Map<String, String> map) {
        User user = checkUtil.tookenCheck();
        if(user == null){ return ResultUtil.error(ResultEnum.ILLEGAL_TOKEN); }
        Integer userId = user.getUserId();
        Integer goodId = Integer.valueOf(map.get("goodId"));

        if(!cartService.cartSelect(userId, goodId)){
            return ResultUtil.error(ResultEnum.EXAMPLE_NOT_EXIST);
        }

        if(cartService.cartDelete(userId, goodId)){
            return ResultUtil.success(ResultEnum.SUCCESS, null);
        }else{
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
        }
    }


}
