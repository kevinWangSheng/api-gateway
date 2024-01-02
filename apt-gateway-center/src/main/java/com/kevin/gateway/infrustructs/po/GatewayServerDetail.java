package com.kevin.gateway.infrustructs.po;

import java.io.Serializable;
import java.util.Date;

/** 网关服务信息详情表
 * @author wang
 * @create 2024-01-02-15:37
 */
public class GatewayServerDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;

    private String groupId;

    private String  gatewayId;

    private String gatewayName;

    private String gatewayAddress;

    private int status;

    private Date createTime;

    private Date updateTime;



    public GatewayServerDetail() {
    }

    public GatewayServerDetail(long id, String groupId, String gatewayId, String gatewayName, String gatewayAddress, int status, Date createTime, Date updateTime) {
        this.id = id;
        this.groupId = groupId;
        this.gatewayId = gatewayId;
        this.gatewayName = gatewayName;
        this.gatewayAddress = gatewayAddress;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
