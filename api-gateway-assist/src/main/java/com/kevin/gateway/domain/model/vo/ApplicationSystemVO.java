package com.kevin.gateway.domain.model.vo;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-04-16:08
 */
public class ApplicationSystemVO {
    private String systemId;

    private String systemType;

    private String systemName;

    private String systemRegistry;

    private List<ApplicationInterfaceVO> interfaceList;

    public ApplicationSystemVO() {
    }

    public ApplicationSystemVO(String systemId, String systemType, String systemName, String systemRegistry) {
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

    public List<ApplicationInterfaceVO> getInterfaceList() {
        return interfaceList;
    }

    public void setInterfaceList(List<ApplicationInterfaceVO> interfaceList) {
        this.interfaceList = interfaceList;
    }
}
