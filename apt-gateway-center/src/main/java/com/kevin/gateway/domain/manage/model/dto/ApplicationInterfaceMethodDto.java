package com.kevin.gateway.domain.manage.model.dto;

import com.kevin.gateway.infrustructs.common.PageRequest;

/**
 * @author wang
 * @create 2024-01-06-21:48
 */
public class ApplicationInterfaceMethodDto extends PageRequest {
    private String systemId;

    private String interfaceId;

    public ApplicationInterfaceMethodDto() {
    }

    public ApplicationInterfaceMethodDto(int pageNum, int pageSize, String systemId, String interfaceId) {
        super(pageNum, pageSize);
        this.systemId = systemId;
        this.interfaceId = interfaceId;
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
}
