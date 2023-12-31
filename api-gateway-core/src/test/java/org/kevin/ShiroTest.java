package org.kevin;

import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.kevin.gateway.authorization.IAuth;
import org.kevin.gateway.authorization.JwtUtil;
import org.kevin.gateway.authorization.auth.AuthService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @create 2023-12-31-0:40
 */
public class ShiroTest {
    @Test
    public void test_auth_service() {
        IAuth auth = new AuthService();
        boolean validate = auth.validate("DPij8iUY", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4aWFvZnVnZSIsImV4cCI6MTcwNDU1OTc1MSwiaWF0IjoxNzAzOTU0OTUxLCJrZXkiOiJ4aWFvZnVnZSJ9.upLSfnQogks98LklMeT8PwCL3ZUq2xcwkGOYa4yuk40");
        System.out.println(validate ? "验证成功" : "验证失败");
    }

    @Test
    public void test_awt() {
        String issuer = "xiaofuge";
        long ttlMillis = 7 * 24 * 60 * 60 * 1000L;
        Map<String, Object> claims = new HashMap<>();
        claims.put("key", "xiaofuge");

        // 编码
        String token = JwtUtil.encode(issuer, ttlMillis, claims);
        System.out.println(token);

        // 解码
        Claims parser = JwtUtil.decoder(token);
        System.out.println(parser.getSubject());
    }

    @Test
    public void test_shiro() {
        // 1. 获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");

        // 2. 得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        // 3. 得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();

        // 4. 默认提供的验证方式；UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken("xiaobai", "456");

        try {
            //5.1、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5.2、身份验证失败
            System.out.println("身份验证失败");
        }

        System.out.println(subject.isAuthenticated() ? "验证成功" : "验证失败");

        // 6. 退出
        subject.logout();
    }
}
