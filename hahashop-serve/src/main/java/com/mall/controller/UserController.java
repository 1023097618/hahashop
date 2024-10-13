package com.mall.controller;


import com.mall.common.Result;
import com.mall.common.ResultEnum;
import com.mall.common.ResultUtil;
import com.mall.entity.User;
import com.mall.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.mall.common.JwtTokenUtil;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Resource
    private UserService userService;//Service封装了Dao与数据库交互

    @RequestMapping("/login")
    //登录功能
    public Result<Object> login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        User userdb = userService.login(username);

        if (userdb == null) {
            return ResultUtil.error(ResultEnum.USER_NOT_EXIST);
        } else if (!password.equals(userdb.getPassword())) {
            return ResultUtil.error(ResultEnum.WRONG_PASSWORD);
        } else {
            userdb.setPassword(null);
            String token = JwtTokenUtil.generateToken(userdb);
            // 封装返回对象，包括token和用户信息
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            return ResultUtil.success(ResultEnum.SUCCESS, data);
        }
    }

    @RequestMapping("/info")
    public Result<Object> info(@RequestParam String token) {
        User user = JwtTokenUtil.decodeToken(token);
        if (user != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("username", user.getUsername());
            return ResultUtil.success(ResultEnum.SUCCESS, data);
        }else{
            return ResultUtil.error(ResultEnum.ILLEGAL_TOKEN);
        }
    }

    @RequestMapping("/changePassword")//大小写不统一
    public Result<Object> changePassword(@RequestBody Map<String, String> map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 检查是否认证和 Principal 是否为 User 类型
        if (authentication != null) {
            User u = (User) authentication.getPrincipal();  // 强制类型转换
            u = userService.login(u.getUsername());
            if(u==null){
                return ResultUtil.error(ResultEnum.ILLEGAL_TOKEN);
            }
            if(map.get("oldPassword").equals(u.getPassword())){//老密码是否相同
                userService.renewPassword(u.getUsername(), map.get("newPassword"));
                return ResultUtil.success(ResultEnum.SUCCESS,null);
            }else{
                return ResultUtil.error(ResultEnum.WRONG_PASSWORD);
            }
        }
        return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
    }

}
