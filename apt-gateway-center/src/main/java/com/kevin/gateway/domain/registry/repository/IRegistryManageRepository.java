package com.kevin.gateway.domain.registry.repository;

import com.kevin.gateway.infrustructs.po.ApplicationInterface;
import com.kevin.gateway.infrustructs.po.ApplicationInterfaceMethod;
import com.kevin.gateway.infrustructs.po.ApplicationSystem;

/**
 * @author wang
 * @create 2024-01-02-18:51
 */
public interface IRegistryManageRepository {
    void registryApplicationSystem(ApplicationSystem applicationSystem);

    void registryApplicationInterface(ApplicationInterface applicationInterface);

    void registryApplicationInterfaceMethod(ApplicationInterfaceMethod applicationInterfaceMethod);
}
