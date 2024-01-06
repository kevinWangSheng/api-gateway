package com.kevin.gateway.domain.operator.model.vo;

import com.kevin.gateway.domain.utilize.ListUtil;
import com.kevin.gateway.infrustructs.po.GatewayServer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 * @create 2024-01-02-16:03
 */
public class GatewayServerDataVO {
    private String systemId;

    private String groupName;

    public GatewayServerDataVO() {
    }

    public GatewayServerDataVO(String systemId, String groupName) {
        this.systemId = systemId;
        this.groupName = groupName;
    }



    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
