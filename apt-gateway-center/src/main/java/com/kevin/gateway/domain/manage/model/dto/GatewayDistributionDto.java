package com.kevin.gateway.domain.manage.model.dto;

import com.kevin.gateway.infrustructs.common.PageRequest;

/**
 * @author wang
 * @create 2024-01-06-20:42
 */
public class GatewayDistributionDto extends PageRequest {
    private String gatewayId;

    private String groupId;

    public GatewayDistributionDto(int pageNum, int pageSize, String gatewayId, String groupId) {
        super(pageNum, pageSize);
        this.gatewayId = gatewayId;
        this.groupId = groupId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
