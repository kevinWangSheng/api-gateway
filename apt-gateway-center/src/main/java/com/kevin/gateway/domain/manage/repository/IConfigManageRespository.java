package com.kevin.gateway.domain.manage.repository;

import com.kevin.gateway.domain.manage.model.vo.GatewayServerDetailVO;
import com.kevin.gateway.domain.manage.model.vo.GatewayServerVO;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-02-16:19
 */
public interface IConfigManageRespository {
    List<GatewayServerVO> queryGatewayServerList();

    boolean registryGatewayServerNode(String groupId,String gatewayId,String gatewayName,String gatewayAddress,Integer available);

    GatewayServerDetailVO queryGatewayServerDetail(String gatewayId,String gatewayAddress);

    boolean updateGatewayStatus(String gatewayId,String gatewayAddress,Integer available);
}
