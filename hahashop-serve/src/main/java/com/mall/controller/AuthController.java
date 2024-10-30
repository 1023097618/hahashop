package com.mall.controller;


import com.mall.common.*;
import com.mall.dao.GoodDao;
import com.mall.entity.User;
import com.mall.service.AuthService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthService authService;//Service封装了Dao与数据库交互
    @Resource
    private CheckUtil checkUtil;
    @Autowired
    private GoodDao goodDao;

    @RequestMapping("/login")
    //登录功能
    public Result<Object> login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        User userdb = authService.login(username);//获取任何必要信息

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

    @RequestMapping("/info")//需要具体实现
    public Result<Object> info(@RequestParam String token) {
        User user = JwtTokenUtil.decodeToken(token);//token在login的时候就传好了，后端给前端什么前端就会回来什么
        if (user != null) {
            Map<String, Object> data = new HashMap<>();
            user = authService.getInfo(user.getUserId());
            data.put("username", user.getUsername());
            data.put("priviliage", user.getPrivilege());
            data.put("userPhone", user.getUserPhone());
            data.put("userAddress", user.getUserAddress());
            data.put("userId", user.getUserId());
            data.put("userRealName", user.getUserRealName());
            return ResultUtil.success(ResultEnum.SUCCESS, data);
        }else{
            return ResultUtil.error(ResultEnum.ILLEGAL_TOKEN);
        }
    }

    @RequestMapping("/changePassword")//大小写不统一
    public Result<Object> changePassword(@RequestBody Map<String, String> map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 检查是否认证和 Principal 是否为 User 类型
        if (checkUtil.tookenCheck()!=null) {
            User u = (User) authentication.getPrincipal();  // 强制类型转换
            u = authService.login(u.getUsername());

            if(map.get("oldPassword").equals(u.getPassword())){//老密码是否相同
                authService.renewPassword(u.getUsername(), map.get("newPassword"));
                return ResultUtil.success(ResultEnum.SUCCESS,null);
            }else{
                return ResultUtil.error(ResultEnum.WRONG_PASSWORD);
            }
        }
        return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
    }

    @RequestMapping("/register")
    public Result<Object> register(@RequestBody User user,@RequestParam String phoneCode) {
        phoneCode = "1111";//目前默认，后续改
        if(phoneCode != "1111"){
            ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
        } else if (authService.userIsExist(user.getUsername())) {//查验存在性
            ResultUtil.error(ResultEnum.USER_HAS_EXIST);
        } else{
            user.setPrivilege(2);
            authService.register(user);
            return ResultUtil.success(ResultEnum.SUCCESS,null);
        }
        return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
    }


}
