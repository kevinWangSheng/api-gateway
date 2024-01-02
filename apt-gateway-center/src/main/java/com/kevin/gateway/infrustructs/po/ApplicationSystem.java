package com.kevin.gateway.infrustructs.po;

import java.util.Date;

/**应用系统信息表
 * @author wang
 * @create 2024-01-02-15:44
 */
public class ApplicationSystem implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    private long id; // 主键自增id

    private String systemId; // 系统标识

    private String systemName; // 系统名称

    private String systemType; // 系统类型 rpc或者是http

    private String systemRegistry;

    private Date createTime;

    private Date updateTime;

    public ApplicationSystem() {
    }

    public ApplicationSystem(long id, String systemId, String systemName, String systemType, String systemRegistry, Date createTime, Date updateTime) {
        this.id = id;
        this.systemId = systemId;
        this.systemName = systemName;
        this.systemType = systemType;
        this.systemRegistry = systemRegistry;
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

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getSystemRegistry() {
        return systemRegistry;
    }

    public void setSystemRegistry(String systemRegistry) {
        this.systemRegistry = systemRegistry;
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
