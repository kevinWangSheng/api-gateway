package com.kevin.gateway.domain.manage.repository;

import com.kevin.gateway.domain.manage.model.vo.*;


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

    List<String> queryGatewayDistributionSystemIdList(String gatewayId);

    List<ApplicationSystemVO> queryApplicationSystemList(List<String> systemIdList);

    List<ApplicationInterfaceVO> queryApplicationInterfaceList(List<String> systemIdList);

    List<ApplicationInterfaceMethodVO> queryApplicationInterfaceMethodList(List<String> systemIdList, List<String> interfaceIds);

    String queryGatewayDistributionGatewayIdBySystemId(String systemId);
}
