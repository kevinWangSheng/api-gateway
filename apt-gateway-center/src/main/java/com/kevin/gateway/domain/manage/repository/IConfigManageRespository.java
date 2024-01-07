package com.kevin.gateway.domain.manage.repository;

import com.kevin.gateway.domain.manage.model.dto.*;
import com.kevin.gateway.domain.manage.model.vo.*;
import com.kevin.gateway.domain.operator.model.vo.*;

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

    List<GatewayServerDataVO> queryGatewayServerPageByGroupId(GatewayServerDto gatewayServerReq);

    List<GatewayServerDetailDataVO> queryGatewayServerDetailPage(GatewayServerDetailDto gatewayDetailRequest);

    List<GatewayDistributionDataVO> queryGatewayDistribution(GatewayDistributionDto gatewayDistributionDto);

    List<ApplicationSystemVO> queryApplicationSystem(ApplicationSystemDto applicationSystemDto);

    List<ApplicationInterfaceDataVO> queryApplicationInterfacePage(ApplicationInterfaceDto applicationInterfaceDto);

    List<ApplicationInterfaceMethodDataVO> queryApplicationInterfaceMethodPage(ApplicationInterfaceMethodDto applicationInterfaceMethodDto);

    List<GatewayServerDetailVO> queryGatewayServerDetailListByGatewayId(String gatewayId);
}
