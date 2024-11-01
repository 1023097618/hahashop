package com.mall.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private Integer categoryId;
    private String categoryName;
    private Integer pid;
}
