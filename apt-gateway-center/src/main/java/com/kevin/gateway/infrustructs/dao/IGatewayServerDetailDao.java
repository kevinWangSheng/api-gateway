package com.kevin.gateway.infrustructs.dao;

import com.kevin.gateway.domain.manage.model.dto.GatewayServerDetailDto;
import com.kevin.gateway.infrustructs.po.GatewayServerDetail;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-02-16:10
 */
public interface IGatewayServerDetailDao {
    void insert(GatewayServerDetail gatewayServerDetail);

    GatewayServerDetail queryGatewayServerDetail(GatewayServerDetail gatewayServerDetail);

    boolean updateGatewayStatus(GatewayServerDetail gatewayServerDetail);

    List<GatewayServerDetail> queryPageByGroupIdAndGatewayId(GatewayServerDetailDto gatewayDetailRequest);
}
