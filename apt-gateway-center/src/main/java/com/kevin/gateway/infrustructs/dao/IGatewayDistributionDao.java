package com.kevin.gateway.infrustructs.dao;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-06-13:56
 */
public interface IGatewayDistributionDao {

    List<String> querySystemIdListByGatewayId(String gatewayId);

    String queryGatewayIdBySystemId(String systemId);
}
