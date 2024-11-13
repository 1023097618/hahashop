package com.mall.dao;

import com.mall.entity.Good;
import com.mall.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodDao {
    //获取所有
    List<Good> goodList(@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum,
                        @Param("goodName") String goodName, @Param("categoryId") Integer categoryId,
                        @Param("privilege") Integer privilege);
    //根据pageSize和pageNum提取出有限个商品，为pageSize*（pageNum-1） 到 pageSize*pageNum

    //获取单项
    Good getGoodById(Integer goodId);//根据商品ID查单个商品，返回除了desc的东西
    //获取详细
    String getDetail(Integer goodId);//根据商品ID返回desc
    //订单数更新
    void buyerNumUpdate(Integer goodId);//根据商品ID让他的buyerNum+=1
    //增删改查
    Integer addGood(@Param("good") Good good, @Param("goodImage") String goodImage);//往表里加，id数据库自增
    Integer updateGood(@Param("good") Good good, @Param("goodImage") String goodImage);//往表里加，good里面会带id，提出来匹配
    Integer deleteGood(Integer goodId);//根据goodid删商品
    Integer countGoods(@Param("privilege") Integer privilege, @Param("goodName") String goodName, @Param("categoryId")Integer categoryId);//统计商品种数
    //商品状态更改
    Integer stateChange(@Param("goodId") Integer goodId, @Param("goodState") Integer goodState);//根据商品Id改成对应的state
    Integer goodNumChange(@Param("goodId") Integer goodId,@Param("changeNum") Integer changeNum);
}
