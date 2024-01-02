package com.kevin.gateway.infrustructs.po;

import java.io.Serializable;
import java.util.Date;

/**应用程序接口信息表
 * @author wang
 * @create 2024-01-02-15:50
 */
public class ApplicationInterface implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id; // 主键自增id

    private String systemId; // 系统标识

    private String interfaceId; // 接口标识

    private String interfaceName; // 接口名称

    private String interfaceVersion; // 接口版本

    private Date createTime; // 创建时间

    private Date updateTime; // 更新时间

    public ApplicationInterface() {
    }

    public ApplicationInterface(long id, String systemId, String interfaceId, String interfaceName, String interfaceVersion, Date createTime, Date updateTime) {
        this.id = id;
        this.systemId = systemId;
        this.interfaceId = interfaceId;
        this.interfaceName = interfaceName;
        this.interfaceVersion = interfaceVersion;
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

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceVersion() {
        return interfaceVersion;
    }

    public void setInterfaceVersion(String interfaceVersion) {
        this.interfaceVersion = interfaceVersion;
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
