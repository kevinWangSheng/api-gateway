package org.kevin.gateway.authorization;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author wang
 * @create 2023-12-31-0:23
 */
public class GatewayAuthorizingToken implements AuthenticationToken {
    private static final long serialVersionUID = 1l;

    private String jwt;

    private String channelId;

    public GatewayAuthorizingToken(String jwt, String channelId) {
        this.jwt = jwt;
        this.channelId = channelId;
    }

    @Override
    public Object getPrincipal() {
        return channelId;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }
}
