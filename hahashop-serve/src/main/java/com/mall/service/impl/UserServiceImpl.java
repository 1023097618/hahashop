package com.mall.service.impl;


import com.mall.dao.UserDao;
import com.mall.entity.User;
import com.mall.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User login(String username) {//数据库获取信息
        return userDao.login(username);
    }


}
