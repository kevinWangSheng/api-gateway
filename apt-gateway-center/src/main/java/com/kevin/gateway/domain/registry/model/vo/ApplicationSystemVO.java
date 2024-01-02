package com.kevin.gateway.domain.registry.model.vo;

import com.kevin.gateway.infrustructs.po.ApplicationSystem;

/**
 * @author wang
 * @create 2024-01-02-17:37
 */
public class ApplicationSystemVO {

    private String systemId; // 系统标识

    private String systemName; // 系统名称

    private String systemType; // 系统类型 rpc或者是http

    private String systemRegistry; // 系统注册地址

    public ApplicationSystemVO() {
    }

    public ApplicationSystemVO(String systemId, String systemName, String systemType, String systemRegistry) {
        this.systemId = systemId;
        this.systemName = systemName;
        this.systemType = systemType;
        this.systemRegistry = systemRegistry;
    }

    public static ApplicationSystem convertToApplicationSystem(ApplicationSystemVO applicationSystemVO) {
        if(applicationSystemVO == null) {
            return null;
        }

        ApplicationSystem applicationSystem = new ApplicationSystem();
        applicationSystem.setSystemId(applicationSystemVO.getSystemId());
        applicationSystem.setSystemName(applicationSystemVO.getSystemName());
        applicationSystem.setSystemType(applicationSystemVO.getSystemType());
        applicationSystem.setSystemRegistry(applicationSystemVO.getSystemRegistry());
        return applicationSystem;
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
}
