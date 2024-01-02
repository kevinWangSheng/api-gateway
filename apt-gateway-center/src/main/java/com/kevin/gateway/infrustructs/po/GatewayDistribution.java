package com.kevin.gateway.infrustructs.po;

import java.io.Serializable;
import java.util.Date;

/** 网关分组信息表
 * @author wang
 * @create 2024-01-02-15:42
 */
public class GatewayDistribution implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;

    private String systemId;

    private String groupId;

    private String systemName;

    private Date createTime;

    private Date updateTime;

    public GatewayDistribution() {
    }

    public GatewayDistribution(long id, String systemId, String groupId, String systemName, Date createTime, Date updateTime) {
        this.id = id;
        this.systemId = systemId;
        this.groupId = groupId;
        this.systemName = systemName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
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
