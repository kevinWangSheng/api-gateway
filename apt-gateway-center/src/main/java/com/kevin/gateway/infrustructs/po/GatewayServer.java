package com.kevin.gateway.infrustructs.po;

import java.io.Serializable;

/** 网关服务信息表
 * @author wang
 * @create 2024-01-02-15:39
 */
public class GatewayServer implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;

    private String systemId;

    private String groupName;

    public GatewayServer() {
    }

    public GatewayServer(long id, String systemId, String groupName) {
        this.id = id;
        this.systemId = systemId;
        this.groupName = groupName;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
