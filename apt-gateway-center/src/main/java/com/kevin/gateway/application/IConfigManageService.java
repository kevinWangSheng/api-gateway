package com.kevin.gateway.application;

import com.kevin.gateway.domain.manage.model.vo.GatewayServerVO;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-02-16:35
 */
public interface IConfigManageService {
    List<GatewayServerVO> queryGatewayServerList();

    boolean registryGatewayServerNode(String groupId,String gatewayId,String gatewayName,String gatewayAddress);
}
