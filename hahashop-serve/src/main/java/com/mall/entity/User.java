package com.mall.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer id;//数据库自增
    private String username;
    private String password;
    private Integer privilege;
}
