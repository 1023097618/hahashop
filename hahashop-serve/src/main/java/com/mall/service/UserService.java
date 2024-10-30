package com.mall.service;

import com.mall.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers(Integer pageSize, Integer pageNum);
    Integer countUsers();
}
