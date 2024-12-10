package com.mall.dao;


import com.mall.entity.Cart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartDao {
    Integer changeCartGoodNum(Integer userId, Integer goodId, Integer cartGoodNum);

    Integer cartAdd(Cart cart);

    List<Cart> getCartGoods(Integer userId, Integer pageSize, Integer pageNum);

    Integer countCart(Integer userId);

    Integer cartDelete(Integer userId, Integer goodId);

    Integer cartSelect(Integer userId, Integer goodId);

}
