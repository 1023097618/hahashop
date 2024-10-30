package com.mall.service.impl;

import com.mall.dao.UserDao;
import com.mall.entity.User;
import com.mall.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public List<User> getUsers(Integer pageSize, Integer pageNum) { return userDao.getUsers(pageSize, pageNum); }

    @Override
    public Integer countUsers() { return userDao.countUsers(); }



}
