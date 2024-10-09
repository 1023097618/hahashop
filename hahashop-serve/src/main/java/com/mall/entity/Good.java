package com.mall.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Good {
    Integer goodId;//数据库自增
    String goodName;
    String goodDesc;
    String goodPrice;
    String goodImage;
    Integer goodState;
    Integer buyerNum;
}
