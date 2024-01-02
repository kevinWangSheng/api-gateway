package com.kevin.gateway.application;

import com.kevin.gateway.config.GatewayServiceProperties;
import com.kevin.gateway.service.RegistryGatewayService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author wang
 * @create 2024-01-02-21:33
 */
public class GatewayApplication implements ApplicationListener<ContextRefreshedEvent> {
    private GatewayServiceProperties properties;

    private RegistryGatewayService gatewayService;

    public GatewayApplication(GatewayServiceProperties properties, RegistryGatewayService gatewayService) {
        this.properties = properties;
        this.gatewayService = gatewayService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 1. 注册网关服务；每一个用于转换 HTTP 协议泛化调用到 RPC 接口的网关都是一个算力，这些算力需要注册网关配置中心
        gatewayService.doRegister(properties.getAddress(),
                properties.getGroupId(),
                properties.getGatewayId(),
                properties.getGatewayName(),
                properties.getGatewayAddress());
    }
}
