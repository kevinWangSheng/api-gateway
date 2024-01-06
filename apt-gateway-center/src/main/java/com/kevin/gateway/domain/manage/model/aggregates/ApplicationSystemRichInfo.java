package com.kevin.gateway.domain.manage.model.aggregates;

import com.kevin.gateway.domain.manage.model.vo.ApplicationSystemVO;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-06-13:22
 */
public class ApplicationSystemRichInfo {
    private String gatewayId;

    private List<ApplicationSystemVO> applicationSystemVOList;

    public ApplicationSystemRichInfo(String gatewayId, List<ApplicationSystemVO> applicationSystemVOList) {
        this.gatewayId = gatewayId;
        this.applicationSystemVOList = applicationSystemVOList;
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
