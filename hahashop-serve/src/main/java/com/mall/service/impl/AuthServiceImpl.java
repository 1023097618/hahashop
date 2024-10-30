package com.mall.service.impl;


import com.mall.dao.AuthDao;
import com.mall.entity.User;
import com.mall.service.AuthService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private AuthDao userDao;

    @Override
    public User login(String username) {//数据库获取信息
        return userDao.login(username);
    }

    @Override
    public Boolean renewPassword(String username, String password) { return userDao.renewPassword(username, password)>0;}

    @Override
    public Boolean register(User user) {return userDao.register(user)>0;}

    @Override
    public Boolean userIsExist(String username) { return userDao.selectUsernameByUsername(username) > 0; }

    @Override
    public User getInfo(Integer userId) { return userDao.getInfo(userId); }
}
