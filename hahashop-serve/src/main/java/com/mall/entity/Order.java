package com.mall.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private Integer orderId;//数据库自增
    private Integer goodId;//传入的时候提取
    private Integer userId;
    private String buyerRealName;
    private String buyerAddress;
    private String buyerPhone;
    private String buyerDesc;
    private Integer buyerGoodsNum;
    private Integer orderState;
    private String orderPrice;
}
