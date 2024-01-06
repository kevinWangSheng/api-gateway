package com.kevin.gateway.application;

import com.kevin.gateway.domain.manage.model.aggregates.ApplicationSystemRichInfo;
import com.kevin.gateway.domain.manage.model.dto.*;
import com.kevin.gateway.domain.manage.model.vo.*;
import com.kevin.gateway.domain.operator.model.vo.ApplicationInterfaceDataVO;
import com.kevin.gateway.domain.operator.model.vo.ApplicationInterfaceMethodDataVO;
import com.kevin.gateway.domain.operator.model.vo.GatewayDistributionDataVO;
import com.kevin.gateway.domain.operator.model.vo.GatewayServerDataVO;
import com.kevin.gateway.domain.operator.model.vo.GatewayServerDetailDataVO;
import com.kevin.gateway.infrustructs.po.GatewayServerDetail;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-02-16:35
 */
public interface IConfigManageService {
    List<GatewayServerVO> queryGatewayServerList();

    boolean registryGatewayServerNode(String groupId,String gatewayId,String gatewayName,String gatewayAddress);

    ApplicationSystemRichInfo queryApplicationSystemRichInfo(String gatewayId);

    String queryGatewayDistributionGatewayIdBySystemId(String systemId);

    List<GatewayServerDataVO> queryGatewayServer(GatewayServerDto gatewayServerDto);

    List<GatewayServerDetailDataVO> queryGatewayServerDetailPage(GatewayServerDetailDto gatewayDetailRequest);

    List<GatewayDistributionDataVO> queryGatewayDistribution(GatewayDistributionDto gatewayDistributionDto);

    List<ApplicationSystemVO> queryApplicationSystem(ApplicationSystemDto applicationSystemDto);

    List<ApplicationInterfaceDataVO> queryApplicationInterfacePage(ApplicationInterfaceDto applicationInterfaceDto);

    List<ApplicationInterfaceMethodDataVO> queryApplicationInterfaceMethodPage(ApplicationInterfaceMethodDto applicationInterfaceMethodDto);
}
