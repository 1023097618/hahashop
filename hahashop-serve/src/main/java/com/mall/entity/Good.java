package com.mall.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Good {
    private Integer goodId;//数据库自增
    private String goodName;
    private String goodDesc;
    private String goodPrice;
    private String[] goodImage;
    private Integer goodState;
    private Integer categoryId;//二级分类ID
    private Integer buyerNum;
    private Integer goodNum;
}
