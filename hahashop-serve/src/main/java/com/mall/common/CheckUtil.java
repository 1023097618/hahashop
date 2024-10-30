package com.mall.common;

import com.mall.entity.User;
import com.mall.service.AuthService;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CheckUtil {
    public boolean isValidPhoneNumber(String phoneNumber) {
//        // 正则表达式，匹配11位的数字
//        String regex = "\\d{11}";
//
//        // 返回正则匹配结果
//        return phoneNumber != null && phoneNumber.matches(regex);
        return true;
    }

    @Resource
    private AuthService authService;
    public User tookenCheck(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = (User) authentication.getPrincipal();  // 强制类型转换
        u = authService.login(u.getUsername());
        return u;
    }



}
