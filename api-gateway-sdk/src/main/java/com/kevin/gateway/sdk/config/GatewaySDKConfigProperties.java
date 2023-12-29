package com.kevin.gateway.sdk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wang
 * @create 2023-12-29-1:24
 */
//@ConfigurationProperties("api-gateway-sdk")
public class GatewaySDKConfigProperties {
    private String systemId;

    private String systemName;

    private String systemRegistry;

    private String address;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemNameName) {
        this.systemName = systemNameName;
    }

    public String getSystemRegistry() {
        return systemRegistry;
    }

    public void setSystemRegistry(String systemRegistry) {
        this.systemRegistry = systemRegistry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
