package com.mall.service.impl;

import com.mall.dao.CartDao;
import com.mall.entity.Cart;
import com.mall.service.CartService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private CartDao cartDao;

    @Override
    public Boolean cartAdd(Cart cart) {
        return cartDao.cartAdd(cart) > 0;
    }

    @Override
    public Boolean changeCartGoodNum(Integer userId, Integer goodId, Integer cartGoodNum) {
        return cartDao.changeCartGoodNum(userId, goodId, cartGoodNum) > 0;
    }

    @Override
    public List<Cart> getCartGoods(Integer userId, Integer pageSize, Integer pageNum) {
        return cartDao.getCartGoods(userId, pageSize, pageNum);
    }

    @Override
    public Integer countCart(Integer userId) {
        return cartDao.countCart(userId);
    }

    @Override
    public Boolean cartDelete(Integer userId, Integer goodId) {
        return cartDao.cartDelete(userId, goodId) > 0;
    }

    @Override
    public Boolean cartSelect(Integer userId, Integer goodId){
        return cartDao.cartSelect(userId, goodId) > 0;
    }
}
