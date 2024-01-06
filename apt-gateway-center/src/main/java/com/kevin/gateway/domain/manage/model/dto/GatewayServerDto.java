package com.kevin.gateway.domain.manage.model.dto;

import com.kevin.gateway.infrustructs.common.PageRequest;

/**
 * @author wang
 * @create 2024-01-06-17:09
 */
public class GatewayServerDto extends PageRequest{
    private String groupId;

    public GatewayServerDto(int pageNum, int pageSize, String groupId) {
        super(pageNum, pageSize);
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
