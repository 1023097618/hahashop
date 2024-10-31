package com.mall.service;


import com.mall.entity.User;

public interface AuthService {
    User login(String username);//数据库获取User信息
    Boolean renewPassword(String username, String password);
    Boolean register(User user);
    Boolean userIsExist(String username);
    User getInfo(String username);
}
