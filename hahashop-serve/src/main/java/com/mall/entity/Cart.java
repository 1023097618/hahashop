package com.mall.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart {
    Integer cartId;
    Integer userId;
    Integer goodId;
    Integer cartGoodNum;

}
