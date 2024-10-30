package com.mall.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    Integer orderId;//数据库自增
    Integer goodId;//传入的时候提取
    Integer userId;
    String buyerRealName;
    String buyerAddress;
    String buyerPhone;
    String buyerDesc;
    Integer buyerGoodsNum;
    Integer orderState;
    String orderPrice;
}
