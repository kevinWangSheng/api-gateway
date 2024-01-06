package com.kevin.gateway.domain.operator.model.vo;

/**
 * @author wang
 * @create 2024-01-02-16:01
 */
public class GatewayServerDetailDataVO {
    private String gatewayId;


    private String gatewayName;

    private String gatewayAddress;

    private Integer status;

    public GatewayServerDetailDataVO() {
    }

    public GatewayServerDetailDataVO(String gatewayId, String gatewayName, String gatewayAddress, Integer status) {
        this.gatewayId = gatewayId;
        this.gatewayName = gatewayName;
        this.gatewayAddress = gatewayAddress;
        this.status = status;
    }


    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public String getGatewayAddress() {
        return gatewayAddress;
    }

    public void setGatewayAddress(String gatewayAddress) {
        this.gatewayAddress = gatewayAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
