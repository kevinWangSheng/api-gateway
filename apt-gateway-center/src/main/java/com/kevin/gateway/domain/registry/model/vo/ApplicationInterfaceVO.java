package com.kevin.gateway.domain.registry.model.vo;

import com.kevin.gateway.infrustructs.po.ApplicationInterface;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wang
 * @create 2024-01-02-17:25
 */
public class ApplicationInterfaceVO {

    private String systemId;

    private String interfaceId;

    private String interfaceName;

    private String interfaceVersion;


    public ApplicationInterfaceVO() {
    }

    public ApplicationInterfaceVO(String systemId, String interfaceId, String interfaceName, String interfaceVersion) {
        this.systemId = systemId;
        this.interfaceId = interfaceId;
        this.interfaceName = interfaceName;
        this.interfaceVersion = interfaceVersion;
    }

    public static ApplicationInterface convertToApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO) {
        if(applicationInterfaceVO == null) {
            return null;
        }

        ApplicationInterface applicationInterface = new ApplicationInterface();
        applicationInterface.setSystemId(applicationInterfaceVO.getSystemId());
        applicationInterface.setInterfaceId(applicationInterfaceVO.getInterfaceId());
        applicationInterface.setInterfaceName(applicationInterfaceVO.getInterfaceName());
        applicationInterface.setInterfaceVersion(applicationInterfaceVO.getInterfaceVersion());
        return applicationInterface;
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
