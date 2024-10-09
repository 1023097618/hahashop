package com.mall.common;

import com.mall.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.security.Key;

public class JwtTokenUtil {

    // 用于加密token的密钥
    private static final String SECRET_KEY = "vyFj22RMsdR4jMAf2wqlX_RZcQDPCWzM3tWMyxy13vQ=";

    private static final Key key = Keys.hmacShaKeyFor(Base64.getUrlDecoder().decode(SECRET_KEY));

    // 生成Token
    public static String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("userId", user.getId());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 设置10小时过期
                .signWith(key)
                .compact();
    }

    // 解码Token，只返回true或false
    public static User decodeToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Extract user information from claims
            String username = claims.get("username", String.class);
            Integer userId = claims.get("userId", Integer.class);

            // Create and return a User object
            User user = new User();
            user.setUsername(username);
            user.setId(userId);

            return user;
        } catch (Exception e) {
            return null; // 解码失败
        }
    }
}
