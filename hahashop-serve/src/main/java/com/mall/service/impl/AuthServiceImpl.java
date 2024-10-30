package com.mall.service.impl;


import com.mall.dao.AuthDao;
import com.mall.entity.User;
import com.mall.service.AuthService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private AuthDao authDao;

    @Override
    public User login(String username) {//数据库获取信息
        return authDao.login(username);
    }

    @Override
    public Boolean renewPassword(String username, String password) { return authDao.renewPassword(username, password)>0;}

    @Override
    public Boolean register(User user) {return authDao.register(user)>0;}

    @Override
    public Boolean userIsExist(String username) { return authDao.selectUsernameByUsername(username) > 0; }

    @Override
    public User getInfo(Integer userId) { return authDao.getInfo(userId); }
}
