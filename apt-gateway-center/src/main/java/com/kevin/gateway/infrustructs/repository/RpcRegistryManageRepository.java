package com.kevin.gateway.infrustructs.repository;

import com.kevin.gateway.domain.registry.repository.IRegistryManageRepository;
import com.kevin.gateway.infrustructs.dao.IApplicationInterfaceDao;
import com.kevin.gateway.infrustructs.dao.IApplicationInterfaceMethodDao;
import com.kevin.gateway.infrustructs.dao.IApplicationSystemDao;
import com.kevin.gateway.infrustructs.po.ApplicationInterface;
import com.kevin.gateway.infrustructs.po.ApplicationInterfaceMethod;
import com.kevin.gateway.infrustructs.po.ApplicationSystem;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2024-01-02-18:53
 */
@Component
public class RpcRegistryManageRepository implements IRegistryManageRepository {
    @Resource
    private IApplicationInterfaceDao applicationInterfaceDao;

    @Resource
    private IApplicationSystemDao applicationSystemDao;

    @Resource
    private IApplicationInterfaceMethodDao applicationInterfaceMethodDao;
    @Override
    public void registryApplicationSystem(ApplicationSystem applicationSystem) {
        if(null != applicationSystem){
            applicationSystemDao.insert(applicationSystem);
        }
    }

    @Override
    public void registryApplicationInterface(ApplicationInterface applicationInterface) {
        if(null != applicationInterface){
            applicationInterfaceDao.insert(applicationInterface);
        }
    }

    @Override
    public void registryApplicationInterfaceMethod(ApplicationInterfaceMethod applicationInterfaceMethod) {
        if(null != applicationInterfaceMethod){
            applicationInterfaceMethodDao.insert(applicationInterfaceMethod);
        }
    }
}
