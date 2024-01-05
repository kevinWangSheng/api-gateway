package com.kevin.gateway.domain.model.aggregates;

import com.kevin.gateway.domain.model.vo.ApplicationSystemVO;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-04-16:12
 */
public class ApplicationSystemRichInfo {
    private String gatewayId;

    private List<ApplicationSystemVO> applicationSystemVOList;

    public ApplicationSystemRichInfo() {
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public List<ApplicationSystemVO> getApplicationSystemVOList() {
        return applicationSystemVOList;
    }

    public void setApplicationSystemVOList(List<ApplicationSystemVO> applicationSystemVOList) {
        this.applicationSystemVOList = applicationSystemVOList;
    }
}
