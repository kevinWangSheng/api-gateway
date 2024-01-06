package com.kevin.gateway.domain.manage.model.dto;

import com.kevin.gateway.infrustructs.common.PageRequest;

/**
 * @author wang
 * @create 2024-01-06-20:57
 */
public class ApplicationSystemDto extends PageRequest{
    private String systemId;

    private String systemName;

    public ApplicationSystemDto(int pageNum, int pageSize, String systemId, String systemName) {
        super(pageNum, pageSize);
        this.systemId = systemId;
        this.systemName = systemName;
    }

    public ApplicationSystemDto() {
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
}
