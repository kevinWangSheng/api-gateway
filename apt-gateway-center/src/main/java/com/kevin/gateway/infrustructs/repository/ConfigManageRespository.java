package com.kevin.gateway.infrustructs.repository;

import com.kevin.gateway.domain.manage.model.dto.*;
import com.kevin.gateway.domain.manage.model.vo.*;
import com.kevin.gateway.domain.manage.repository.IConfigManageRespository;
import com.kevin.gateway.domain.operator.model.vo.*;
import com.kevin.gateway.infrustructs.dao.*;
import com.kevin.gateway.infrustructs.po.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 * @create 2024-01-02-16:23
 */
@Component
public class ConfigManageRespository implements IConfigManageRespository {
    @Resource
    private IGatewayServerDao gatewayServerDao;

    @Resource
    private IGatewayServerDetailDao gatewayServerDetailDao;

    @Resource
    private IGatewayDistributionDao gatewayDistributionDao;

    @Resource
    private IApplicationSystemDao systemDao;

    @Resource
    private IApplicationInterfaceDao interfaceDao;

    @Resource
    private IApplicationInterfaceMethodDao methodDao;

    @Override
    public List<GatewayServerVO> queryGatewayServerList() {
        List<GatewayServer> gatewayServers = gatewayServerDao.queryGatewayServerList();
        // 将对应的类型转换成为VO并返回
        return GatewayServerVO.convertFor(gatewayServers);
    }

    @Override
    public boolean registryGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress, Integer available) {
        GatewayServerDetail gatewayServerDetail = new GatewayServerDetail();
        gatewayServerDetail.setGatewayAddress(gatewayAddress);
        gatewayServerDetail.setGatewayName(gatewayName);
        gatewayServerDetail.setGatewayId(gatewayId);
        gatewayServerDetail.setGroupId(groupId);
        gatewayServerDetail.setStatus(available);
        gatewayServerDetailDao.insert(gatewayServerDetail);
        // 没有出错表示处理成功，返回成功
        return true;
    }

    @Override
    public GatewayServerDetailVO queryGatewayServerDetail(String gatewayId, String gatewayAddress) {
        GatewayServerDetail req = new GatewayServerDetail();
        req.setGatewayId(gatewayId);
        req.setGatewayAddress(gatewayAddress);
        GatewayServerDetail gatewayServerDetail = gatewayServerDetailDao.queryGatewayServerDetail(req);
        return GatewayServerDetailVO.convertFor(gatewayServerDetail);
    }

    @Override
    public boolean updateGatewayStatus(String gatewayId, String gatewayAddress, Integer available) {
        GatewayServerDetail gatewayServerDetail = new GatewayServerDetail();
        gatewayServerDetail.setGatewayId(gatewayId);
        gatewayServerDetail.setGatewayAddress(gatewayAddress);
        gatewayServerDetail.setStatus(available);
        return gatewayServerDetailDao.updateGatewayStatus(gatewayServerDetail);
    }

    @Override
    public List<String> queryGatewayDistributionSystemIdList(String gatewayId) {
        List<String> systemIdList = gatewayDistributionDao.querySystemIdListByGatewayId(gatewayId);
        return systemIdList;
    }

    @Override
    public List<ApplicationSystemVO> queryApplicationSystemList(List<String> systemIdList) {
        List<ApplicationSystem> applicationSystems = systemDao.querySystemListBySystemIds(systemIdList);

        List<ApplicationSystemVO> applicationSystemVOList = new ArrayList<>();
        for(ApplicationSystem applicationSystem : applicationSystems){
            ApplicationSystemVO applicationSystemVO = new ApplicationSystemVO();
            applicationSystemVO.setSystemId(applicationSystem.getSystemId());
            applicationSystemVO.setSystemName(applicationSystem.getSystemName());
            applicationSystemVO.setSystemRegistry(applicationSystem.getSystemRegistry());
            applicationSystemVO.setSystemType(applicationSystem.getSystemType());
            applicationSystemVOList.add(applicationSystemVO);
        }
        return applicationSystemVOList;
    }

    @Override
    public List<ApplicationInterfaceVO> queryApplicationInterfaceList(List<String> systemIdList) {
        List<ApplicationInterface> applicationInterfaces = interfaceDao.queryInterfaceListBySystemIds(systemIdList);

        List<ApplicationInterfaceVO> interfaceVOList = new ArrayList<>();

        for(ApplicationInterface applicationInterface : applicationInterfaces){
            ApplicationInterfaceVO applicationInterfaceVO = new ApplicationInterfaceVO();
            applicationInterfaceVO.setInterfaceId(applicationInterface.getInterfaceId());
            applicationInterfaceVO.setInterfaceName(applicationInterface.getInterfaceName());
            applicationInterfaceVO.setInterfaceVersion(applicationInterface.getInterfaceVersion());
            interfaceVOList.add(applicationInterfaceVO);
        }
        return interfaceVOList;
    }

    @Override
    public List<ApplicationInterfaceMethodVO> queryApplicationInterfaceMethodList(List<String> systemIdList, List<String> interfaceIds) {
        List<ApplicationInterfaceMethod> methodList = methodDao.queryMethodListBySystemIdsAndInterfaceIds(systemIdList, interfaceIds);
        if(null == methodList || methodList.isEmpty()){
            return null;
        }
        List<ApplicationInterfaceMethodVO> methodVOList = new ArrayList<>();
        for(ApplicationInterfaceMethod method:methodList){
            ApplicationInterfaceMethodVO methodVO = new ApplicationInterfaceMethodVO();
            methodVO.setInterfaceId(method.getInterfaceId());
            methodVO.setMethodId(method.getMethodId());
            methodVO.setMethodName(method.getMethodName());
            methodVO.setParameterTypes(method.getParameterTypes());
            methodVO.setAuth(method.getAuth());
            methodVO.setHttpCommandType(method.getHttpCommandType());
            methodVO.setUri(method.getUri());
            methodVO.setSystemId(method.getSystemId());
            methodVOList.add(methodVO);
        }
        return methodVOList;
    }

    @Override
    public String queryGatewayDistributionGatewayIdBySystemId(String systemId) {
        if(StringUtils.isBlank(systemId)){
            return null;
        }
        return gatewayDistributionDao.queryGatewayIdBySystemId(systemId);
    }

    @Override
    public List<GatewayServerDataVO> queryGatewayServerPageByGroupId(GatewayServerDto gatewayServerReq) {
        if(null == gatewayServerReq){
            return null;
        }
        List<GatewayServer> gatewayServers = gatewayServerDao.queryPageByGroupId(gatewayServerReq);
        List<GatewayServerDataVO> serverVOS = new ArrayList<>();
        for(GatewayServer gatewayServer:gatewayServers){
            GatewayServerDataVO serverVO = new GatewayServerDataVO();
            serverVO.setSystemId(gatewayServer.getSystemId());
            serverVO.setGroupName(gatewayServer.getGroupName());
            serverVOS.add(serverVO);
        }
        return serverVOS;
    }

    @Override
    public List<GatewayServerDetailDataVO> queryGatewayServerDetailPage(GatewayServerDetailDto gatewayDetailRequest) {
        List<GatewayServerDetail> gatewayDetails = gatewayServerDetailDao.queryPageByGroupIdAndGatewayId(gatewayDetailRequest);
        List<GatewayServerDetailDataVO> detailVOList = new ArrayList<>();
        for(GatewayServerDetail detail:gatewayDetails){
            GatewayServerDetailDataVO detailVO = new GatewayServerDetailDataVO();
            detailVO.setGatewayId(detail.getGatewayId());
            detailVO.setGatewayName(detail.getGatewayName());
            detailVO.setGatewayAddress(detail.getGatewayAddress());
            detailVO.setStatus(detail.getStatus());
            detailVOList.add(detailVO);
        }
        return detailVOList;
    }

    @Override
    public List<GatewayDistributionDataVO> queryGatewayDistribution(GatewayDistributionDto gatewayDistributionDto) {
        List<GatewayDistribution> gatewayDetails = gatewayDistributionDao.queryPageByGroupIdAndGatewayId(gatewayDistributionDto);
        List<GatewayDistributionDataVO> distributionVOList = new ArrayList<>();
        for(GatewayDistribution detail:gatewayDetails){
            GatewayDistributionDataVO detailVO = new GatewayDistributionDataVO();
            detailVO.setGatewayId(detail.getGatewayId());
            detailVO.setSystemId(detail.getSystemId());
            detailVO.setSystemName(detail.getSystemName());
            detailVO.setGroupId(detail.getGroupId());
            distributionVOList.add(detailVO);
        }
        return distributionVOList;
    }

    @Override
    public List<ApplicationSystemVO> queryApplicationSystem(ApplicationSystemDto applicationSystemDto) {
        List<ApplicationSystem> applicationSystems = systemDao.queryPageBySystemIdAndName(applicationSystemDto);
        List<ApplicationSystemVO> systemVOList = new ArrayList<>();
        for(ApplicationSystem system:applicationSystems){
            ApplicationSystemVO systemVO = new ApplicationSystemVO();
            systemVO.setSystemId(system.getSystemId());
            systemVO.setSystemName(system.getSystemName());
            systemVO.setSystemRegistry(system.getSystemRegistry());
            systemVO.setSystemType(system.getSystemType());
            systemVOList.add(systemVO);
        }
        return systemVOList;
    }

    @Override
    public List<ApplicationInterfaceDataVO> queryApplicationInterfacePage(ApplicationInterfaceDto applicationInterfaceDto) {
        List<ApplicationInterface> interfaceList = interfaceDao.queryPageBySystemIdAndInterfaceId(applicationInterfaceDto);
        List<ApplicationInterfaceDataVO> interfaceVOList = new ArrayList<>();
        for(ApplicationInterface itf:interfaceList){
            ApplicationInterfaceDataVO itfVO = new ApplicationInterfaceDataVO();
            itfVO.setSystemId(itf.getSystemId());
            itfVO.setInterfaceId(itf.getInterfaceId());
            itfVO.setInterfaceName(itf.getInterfaceName());
            itfVO.setInterfaceVersion(itf.getInterfaceVersion());
            interfaceVOList.add(itfVO);
        }
        return interfaceVOList;
    }

    @Override
    public List<ApplicationInterfaceMethodDataVO> queryApplicationInterfaceMethodPage(ApplicationInterfaceMethodDto applicationInterfaceMethodDto) {
        List<ApplicationInterfaceMethod> methodList = methodDao.queryPageBySystemIdAndInterfaceId(applicationInterfaceMethodDto);
        List<ApplicationInterfaceMethodDataVO> methodVOList = new ArrayList<>();
        for(ApplicationInterfaceMethod method:methodList){
            ApplicationInterfaceMethodDataVO methodVO = new ApplicationInterfaceMethodDataVO();
            methodVO.setSystemId(method.getSystemId());
            methodVO.setInterfaceId(method.getInterfaceId());
            methodVO.setMethodId(method.getMethodId());
            methodVO.setMethodName(method.getMethodName());
            methodVO.setParameterTypes(method.getParameterTypes());
            methodVO.setAuth(method.getAuth());
            methodVO.setHttpCommandType(method.getHttpCommandType());
            methodVO.setUri(method.getUri());
            methodVOList.add(methodVO);
        }
        return methodVOList;
    }

    @Override
    public List<GatewayServerDetailVO> queryGatewayServerDetailListByGatewayId(String gatewayId) {
        List<GatewayServerDetail> detailList = gatewayServerDetailDao.queryListByGatewayId(gatewayId);
        List<GatewayServerDetailVO> detailVOList = new ArrayList<>();

        for(GatewayServerDetail detail:detailList){
            GatewayServerDetailVO detailVO = new GatewayServerDetailVO();
            detailVO.setGatewayId(detail.getGatewayId());
            detailVO.setGatewayName(detail.getGatewayName());
            detailVO.setGatewayAddress(detail.getGatewayAddress());
            detailVO.setStatus(detail.getStatus());
            detailVO.setId(detail.getId());
            detailVO.setGroupId(detail.getGroupId());
            detailVO.setCreateTime(detail.getCreateTime());
            detailVO.setUpdateTime(detail.getUpdateTime());
            detailVOList.add(detailVO);
        }
        return detailVOList;
    }
}
