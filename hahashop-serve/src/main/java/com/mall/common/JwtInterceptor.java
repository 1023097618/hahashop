package com.mall.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mall.entity.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Enumeration;

@Component
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})//security被启用，有密码，很碍事
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            System.out.println("OPTIONS请求，放行");
            return true;
        }
        // 从请求头中获取 x-hahashop-token
        String token = request.getHeader("x-hahashop-token");

        // 如果token存在并且解码成功
        if (token != null) {
            User user = JwtTokenUtil.decodeToken(token); // 使用 decodeToken 方法解码 token
            if (user != null) {
                // 直接使用 User 对象的用户名注入到 Spring Security 上下文中
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
                // 将 Authentication 对象注入到 Spring Security 上下文
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return true; // 继续请求处理,SecurityContextHolder.getContext().getAuthentication()获取存储信息
            }
        }else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            String json = JSONObject.toJSONString(ResultUtil.error(ResultEnum.ILLEGAL_TOKEN));
            response.getWriter().write(json);
            return false; // 阻止请求继续
        }
        return false;
    }
}