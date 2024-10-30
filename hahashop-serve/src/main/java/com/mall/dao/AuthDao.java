package com.mall.dao;

import com.mall.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthDao {
    User login(@Param("username") String username);
    Integer renewPassword(@Param("username")String username, @Param("password") String password);

    Integer register(@Param(("user")) User user);
    Integer selectUsernameByUsername(@Param("username") String username);


    User getInfo(@Param("userid") Integer userId);
}
