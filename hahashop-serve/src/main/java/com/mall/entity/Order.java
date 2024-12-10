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
    private Integer orderState;//0">客户下单 1">订单被卖家取消 2">订单完成 3">订单被买家取消 4">商家确认 5">备货完成 6">开始发货
    private String orderPrice;
}
