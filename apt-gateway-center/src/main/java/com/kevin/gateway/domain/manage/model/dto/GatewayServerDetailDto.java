package com.kevin.gateway.domain.manage.model.dto;

import com.kevin.gateway.infrustructs.common.PageRequest;

/**
 * @author wang
 * @create 2024-01-06-17:50
 */
public class GatewayServerDetailDto extends PageRequest {
    private String groupId;

    private String gatewayId;
    public GatewayServerDetailDto(String groupId, String gatewayId,int pageNum, int pageSize) {
        super(pageNum, pageSize);
        this.gatewayId = gatewayId;
        this.groupId = groupId;
    }

    public GatewayServerDetailDto(int pageNum, int pageSize) {
        super(pageNum, pageSize);
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }
}
