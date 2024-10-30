package com.mall.dao;


import com.mall.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> getUsers(@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);
    Integer countUsers();



}
