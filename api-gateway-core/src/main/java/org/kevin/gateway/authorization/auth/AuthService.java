package org.kevin.gateway.authorization.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.kevin.gateway.authorization.GatewayAuthorizingRelam;
import org.kevin.gateway.authorization.GatewayAuthorizingToken;
import org.kevin.gateway.authorization.IAuth;


/**
 * @author wang
 * @create 2023-12-31-0:26
 */
public class AuthService implements IAuth {
    private Subject subject;

    public AuthService(){
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(new GatewayAuthorizingRelam());
        SecurityUtils.setSecurityManager(securityManager);
        this.subject = SecurityUtils.getSubject();
    }

    @Override
    public boolean validate(String id, String token) {

        try {
            // 身份验证
            subject.login(new GatewayAuthorizingToken(token,id));
            // 返回结果
            return subject.isAuthenticated();
        } finally {
            subject.logout();
        }
    }
}
