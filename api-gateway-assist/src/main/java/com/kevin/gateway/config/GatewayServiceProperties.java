package com.kevin.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wang
 * @create 2024-01-02-21:28
 */
@ConfigurationProperties("api-gateway")
public class GatewayServiceProperties {
    private String groupId;
    // 网关ID
    private String gatewayId;
    // 网关名称
    private String gatewayName;

    // 注册中心地址
    private String address;

    // 网关地址
    private String gatewayAddress;

    public GatewayServiceProperties() {
    }

    public GatewayServiceProperties(String groupId, String gatewayId, String gatewayName, String address, String gatewayAddress) {
        this.groupId = groupId;
        this.gatewayId = gatewayId;
        this.gatewayName = gatewayName;
        this.address = address;
        this.gatewayAddress = gatewayAddress;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGatewayAddress() {
        return gatewayAddress;
    }

    public void setGatewayAddress(String gatewayAddress) {
        this.gatewayAddress = gatewayAddress;
    }
}
