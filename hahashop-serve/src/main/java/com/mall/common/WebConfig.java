package com.mall.common;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**") // 拦截所有路径
                .excludePathPatterns("/auth/login", "/register","/good/list","/order/buy", "/auth/info","/good/detail"); // 排除登录和注册接口
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 设置允许请求方式
                .allowedMethods("*")
                // 是否允许证书（cookies）
                .allowCredentials(true)
                // 预请求的结果能被缓存多久
                .maxAge(3600)
                // 设置允许的请求头
                .allowedHeaders("*");
    }
}
