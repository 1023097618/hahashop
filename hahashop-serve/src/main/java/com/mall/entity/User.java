package com.mall.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer userId;//数据库自增
    private String username;
    private String password;
    private Integer privilege;
    private String userPhone;
    private String userAddress;
    private String userRealName;
}
