package com.kevin.gateway.domain.operator.model.vo;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-04-16:08
 */
public class ApplicationSystemDataVO {
    private String systemId;

    private String systemType;

    private String systemName;

    private String systemRegistry;


    public ApplicationSystemDataVO() {
    }

    public ApplicationSystemDataVO(String systemId, String systemType, String systemName, String systemRegistry) {
        this.systemId = systemId;
        this.systemType = systemType;
        this.systemName = systemName;
        this.systemRegistry = systemRegistry;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemRegistry() {
        return systemRegistry;
    }

    public void setSystemRegistry(String systemRegistry) {
        this.systemRegistry = systemRegistry;
    }

}
