package com.mall.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.Charset;
import java.util.Enumeration;

@Component
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
        if (token != null && JwtTokenUtil.decodeToken(token)!=null) {
            request.setAttribute("isTokenValid", true); // 设置解码结果为true
            return true; // 继续请求处理
        } else {
            request.setAttribute("isTokenValid", false); // 设置解码结果为false
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            String json = JSONObject.toJSONString(ResultUtil.error(ResultEnum.ILLEGAL_TOKEN));
            response.getWriter().write(json);
            return false; // 阻止请求继续
        }
    }
}
