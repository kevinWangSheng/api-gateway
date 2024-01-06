package com.kevin.gateway.domain.operator.model.vo;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-04-16:07
 */
public class ApplicationInterfaceDataVO {
    private String systemId;

    private String interfaceId;

    private String interfaceName;

    private String interfaceVersion;


    public ApplicationInterfaceDataVO() {
    }

    public ApplicationInterfaceDataVO(String systemId, String interfaceId, String interfaceName, String interfaceVersion) {
        this.systemId = systemId;
        this.interfaceId = interfaceId;
        this.interfaceName = interfaceName;
        this.interfaceVersion = interfaceVersion;
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

}
