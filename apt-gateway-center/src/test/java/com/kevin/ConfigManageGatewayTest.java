package com.kevin;

import com.kevin.gateway.application.IConfigManageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2024-01-02-16:54
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfigManageGatewayTest {
    @Resource
    private IConfigManageService configManageService;
    @Test
    public void testRegistryGatewayServerNode() {
        configManageService.registryGatewayServerNode("10001","api-gateway-10001","电商支付网关","127.0.0.1");

        configManageService.registryGatewayServerNode("10002","api-gateway-10002","电商发货网关","127.0.0.1");

        configManageService.registryGatewayServerNode("10003","api-gateway-10003","电商配送网关","127.0.0.1");
    }
}
