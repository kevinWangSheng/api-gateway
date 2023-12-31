package org.kevin.gateway.authorization;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @create 2023-12-31-0:11
 */
public class JwtUtil {
    private static final String signKey = "B*B^5Fe";
    
    
    public static String encode(String issure, long experTimeMill, Map<String,Object> claims){
        if(claims == null){
            claims = new HashMap<>();
        }

        long currentedTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentedTimeMillis);
        JwtBuilder builder = Jwts.builder()
                // 设置载荷
                .setClaims(claims)
                // 设置签发人
                .setSubject(issure)
                // 设置签发时间
                .setIssuedAt(now)
                // 设置生成签名的算法和秘钥
                .signWith(SignatureAlgorithm.HS256, signKey);
        if(experTimeMill>0){
            builder.setExpiration(new Date(currentedTimeMillis+experTimeMill));
        }
        return builder.compact();
    }

    public static Claims decoder(String token){
        return Jwts.parser()
                // 设置签名的秘钥，这个秘钥必须和生成签名的秘钥一致，否则会报错
                .setSigningKey(signKey)
                // 对token进行解密
                .parseClaimsJws(token)
                .getBody();
    }
}
