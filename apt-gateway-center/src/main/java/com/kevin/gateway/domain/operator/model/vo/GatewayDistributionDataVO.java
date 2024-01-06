package com.kevin.gateway.domain.operator.model.vo;

/**
 * @author wang
 * @create 2024-01-06-20:41
 */
public class GatewayDistributionDataVO {
    private String systemId;

    private String gatewayId;

    private String groupId;

    private String systemName;

    public GatewayDistributionDataVO(String systemId, String gatewayId, String groupId, String systemName) {
        this.systemId = systemId;
        this.gatewayId = gatewayId;
        this.groupId = groupId;
        this.systemName = systemName;
    }

    public GatewayDistributionDataVO() {
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
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

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
}
