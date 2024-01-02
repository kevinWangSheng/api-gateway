package com.kevin.gateway.domain.manage.service;

import com.kevin.gateway.application.IConfigManageService;
import com.kevin.gateway.domain.manage.model.vo.GatewayServerDetailVO;
import com.kevin.gateway.domain.manage.model.vo.GatewayServerVO;
import com.kevin.gateway.domain.manage.repository.IConfigManageRespository;
import com.kevin.gateway.infrustructs.common.Constance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wang
 * @create 2024-01-02-16:36
 */
@Service
public class ConfigManageServiceImpl implements IConfigManageService {
    @Resource
    private IConfigManageRespository configManageRespository;
    @Override
    public List<GatewayServerVO> queryGatewayServerList() {
        return configManageRespository.queryGatewayServerList();
    }

    @Override
    public boolean registryGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress) {
        GatewayServerDetailVO gatewayServerDetailVO = configManageRespository.queryGatewayServerDetail(gatewayId, gatewayAddress);
        boolean result;
        if(null == gatewayServerDetailVO){
            try {
                result =  configManageRespository.registryGatewayServerNode(groupId, gatewayId, gatewayName, gatewayAddress, Constance.GatewayStatus.Available);
            } catch (Exception e) {
                result = configManageRespository.updateGatewayStatus(gatewayId,gatewayAddress,Constance.GatewayStatus.Available);
            }
        }else{
            result = configManageRespository.updateGatewayStatus(gatewayId,gatewayAddress,Constance.GatewayStatus.Available);
        }
        return  result;
    }
}
