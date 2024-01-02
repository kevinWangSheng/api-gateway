package com.kevin.gateway.infrustructs.repository;

import com.kevin.gateway.domain.manage.model.vo.GatewayServerDetailVO;
import com.kevin.gateway.domain.manage.model.vo.GatewayServerVO;
import com.kevin.gateway.domain.manage.repository.IConfigManageRespository;
import com.kevin.gateway.domain.utilize.ListUtil;
import com.kevin.gateway.infrustructs.dao.IGatewayServerDao;
import com.kevin.gateway.infrustructs.dao.IGatewayServerDetailDao;
import com.kevin.gateway.infrustructs.po.GatewayServer;
import com.kevin.gateway.infrustructs.po.GatewayServerDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
}
