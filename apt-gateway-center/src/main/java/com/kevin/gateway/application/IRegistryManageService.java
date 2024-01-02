package com.kevin.gateway.application;

import com.kevin.gateway.domain.registry.model.vo.ApplicationInterfaceMethodVO;
import com.kevin.gateway.domain.registry.model.vo.ApplicationInterfaceVO;
import com.kevin.gateway.domain.registry.model.vo.ApplicationSystemVO;

/**
 * @author wang
 * @create 2024-01-02-18:55
 */
public interface IRegistryManageService {
    void registryApplication(ApplicationSystemVO applicationSystemVO);

    void registryApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO);

    void registryApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO);
}
