package com.mall.service;

import com.mall.entity.Good;
import com.mall.entity.Order;

import java.util.List;

public interface GoodService {
    //获取所有
    List<Good> goodList(Integer pageSize, Integer pageNum);
    //获取单项
    Good getGoodById(Integer id);
    //获取详细
    String getDetail(Integer goodid);
    //增删改查
    Boolean addGood(Good good);
    Boolean updateGood(Good good);
    Boolean deleteGood(Integer goodid);
    //商品状态更改
    Boolean stateChange(Integer goodid,Integer state);
    Integer countGoods();
}
