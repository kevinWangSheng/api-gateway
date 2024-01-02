package com.kevin.gateway.domain.manage.model.vo;

import com.kevin.gateway.domain.utilize.ListUtil;
import com.kevin.gateway.infrustructs.po.GatewayServer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 * @create 2024-01-02-16:03
 */
public class GatewayServerVO {
    private String systemId;

    private String groupName;

    public GatewayServerVO() {
    }

    public GatewayServerVO(String systemId, String groupName) {
        this.systemId = systemId;
        this.groupName = groupName;
    }

    public static List<GatewayServerVO> convertFor(List<GatewayServer> gatewayServers) {
        if(ListUtil.isEmpty(gatewayServers)){
            return null;
        }
        List<GatewayServerVO> gatewayServerVOS = new ArrayList<>();
        for(GatewayServer gatewayServer : gatewayServers){
            GatewayServerVO gatewayServerVO = new GatewayServerVO();
            gatewayServerVO.setGroupName(gatewayServer.getGroupName());
            gatewayServerVO.setSystemId(gatewayServer.getSystemId());
            gatewayServerVOS.add(gatewayServerVO);
        }
        return gatewayServerVOS;
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
