package com.kevin.gateway.domain.manage.service;

import com.kevin.gateway.application.IConfigManageService;
import com.kevin.gateway.domain.manage.model.aggregates.ApplicationSystemRichInfo;
import com.kevin.gateway.domain.manage.model.vo.*;
import com.kevin.gateway.domain.manage.repository.IConfigManageRespository;

import com.kevin.gateway.infrustructs.common.Constance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ApplicationSystemRichInfo queryApplicationSystemRichInfo(String gatewayId) {
        if(StringUtils.isBlank(gatewayId)){
            return null;
        }
        // 查询出对应该网关的系统ID集合
        List<String> systemIdList = configManageRespository.queryGatewayDistributionSystemIdList(gatewayId);
        // 查询系统ID对应的系统列表信息
        List<ApplicationSystemVO> applicationSystemVOList = configManageRespository.queryApplicationSystemList(systemIdList);
        // 如果没有对应的网关信息，则直接返回
        if(null == applicationSystemVOList || applicationSystemVOList.isEmpty()){
            return new ApplicationSystemRichInfo(gatewayId,applicationSystemVOList);
        }
        // 查询系统下的接口信息
        List<ApplicationInterfaceVO> applicationInterfaceVOList = configManageRespository.queryApplicationInterfaceList(systemIdList);
        // 如果没有对应的接口信息，则直接返回
        if(null == applicationSystemVOList || applicationSystemVOList.isEmpty()){
            return new ApplicationSystemRichInfo(gatewayId,applicationSystemVOList);
        }
        // 将接口信息按照系统ID进行分组成为接口id集合
        List<String> interfaceIds = applicationInterfaceVOList.stream().map(itf -> itf.getInterfaceId()).filter(interfaceId -> StringUtils.isNoneBlank(interfaceId)).collect(Collectors.toList());

        // 通过接口id集合查询接口下的方法信息
        List<ApplicationInterfaceMethodVO> applicationInterfaceMethodVOList = configManageRespository.queryApplicationInterfaceMethodList(systemIdList,interfaceIds);

        // 将最终的结果进行组装
        return buildApplicationSystemVOList(gatewayId, applicationSystemVOList, applicationInterfaceVOList, applicationInterfaceMethodVOList);

    }

    @Override
    public String queryGatewayDistributionGatewayIdBySystemId(String systemId) {
        return configManageRespository.queryGatewayDistributionGatewayIdBySystemId(systemId);
    }

    private ApplicationSystemRichInfo buildApplicationSystemVOList(String gatewayId,List<ApplicationSystemVO> applicationSystemVOList, List<ApplicationInterfaceVO> applicationInterfaceVOList, List<ApplicationInterfaceMethodVO> applicationInterfaceMethodVOList) {
        for(ApplicationSystemVO systemVO:applicationSystemVOList){
            List<ApplicationInterfaceVO> interfaceVOS = applicationInterfaceVOList.stream().filter(itf -> itf.getSystemId().equals(systemVO.getSystemId())).collect(Collectors.toList());
            for(ApplicationInterfaceVO interfaceVO: interfaceVOS){
                List<ApplicationInterfaceMethodVO> methodVOS = applicationInterfaceMethodVOList.stream().filter(methodVO -> methodVO.getInterfaceId().equals(interfaceVO.getInterfaceId()) && methodVO.getSystemId().equals(interfaceVO.getSystemId())).collect(Collectors.toList());
                interfaceVO.setMethodList(methodVOS);
            }
            systemVO.setInterfaceList(interfaceVOS);
        }
        return new ApplicationSystemRichInfo(gatewayId,applicationSystemVOList);
    }
}
