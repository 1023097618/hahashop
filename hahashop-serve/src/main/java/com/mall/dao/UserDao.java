package com.mall.dao;

import com.mall.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User login(String username);//获取整个User
}
