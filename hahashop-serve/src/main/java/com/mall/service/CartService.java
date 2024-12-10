package com.mall.service;

import com.mall.entity.Cart;

import java.util.List;

public interface CartService {
    Boolean cartAdd(Cart cart);
    Boolean changeCartGoodNum(Integer userId, Integer goodId, Integer cartGoodNum);

    List<Cart> getCartGoods(Integer userId, Integer pageSize, Integer pageNum);

    Integer countCart(Integer userId);

    Boolean cartDelete(Integer userId, Integer goodId);

    Boolean cartSelect(Integer userId, Integer goodId);

}
