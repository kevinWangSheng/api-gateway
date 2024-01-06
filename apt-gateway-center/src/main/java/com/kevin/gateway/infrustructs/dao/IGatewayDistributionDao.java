package com.kevin.gateway.infrustructs.dao;

import com.kevin.gateway.domain.manage.model.dto.GatewayDistributionDto;
import com.kevin.gateway.domain.manage.model.dto.GatewayServerDetailDto;
import com.kevin.gateway.domain.manage.model.vo.GatewayDistributionVO;
import com.kevin.gateway.infrustructs.po.GatewayDistribution;
import com.kevin.gateway.infrustructs.po.GatewayServerDetail;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-06-13:56
 */
public interface IGatewayDistributionDao {

    List<String> querySystemIdListByGatewayId(String gatewayId);

    String queryGatewayIdBySystemId(String systemId);

    List<GatewayDistribution> queryPageByGroupIdAndGatewayId(GatewayDistributionDto gatewayDistributionDto);
}
