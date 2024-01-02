package com.kevin.gateway.domain.manage.model.vo;

import com.kevin.gateway.infrustructs.po.GatewayServerDetail;

/**
 * @author wang
 * @create 2024-01-02-16:01
 */
public class GatewayServerDetailVO {
    private String gatewayId;


    private String gatewayName;

    private String gatewayAddress;

    private Integer status;

    public GatewayServerDetailVO() {
    }

    public GatewayServerDetailVO(String gatewayId, String gatewayName, String gatewayAddress, Integer status) {
        this.gatewayId = gatewayId;
        this.gatewayName = gatewayName;
        this.gatewayAddress = gatewayAddress;
        this.status = status;
    }

    public static GatewayServerDetailVO convertFor(GatewayServerDetail gatewayServerDetail) {
        if(gatewayServerDetail == null) return null;
        GatewayServerDetailVO gatewayServerDetailVO = new GatewayServerDetailVO();
        gatewayServerDetailVO.setGatewayAddress(gatewayServerDetail.getGatewayAddress());
        gatewayServerDetailVO.setGatewayName(gatewayServerDetail.getGatewayName());
        gatewayServerDetailVO.setGatewayId(gatewayServerDetail.getGatewayId());
        gatewayServerDetailVO.setStatus(gatewayServerDetail.getStatus());
        return gatewayServerDetailVO;
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
