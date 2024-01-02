package com.kevin.gateway.config;

import com.kevin.gateway.application.GatewayApplication;
import com.kevin.gateway.service.RegistryGatewayService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wang
 * @create 2024-01-02-21:25
 */
@EnableConfigurationProperties(GatewayServiceProperties.class)
@Configuration
public class GatewayAutoConfig {

    @Bean
    public RegistryGatewayService registryGatewayService(){
        return new RegistryGatewayService();
    }

    @Bean
    public GatewayApplication gatewayApplication(GatewayServiceProperties properties, RegistryGatewayService registryGatewayService){
        return new GatewayApplication(properties, registryGatewayService);
    }
}
