package com.kevin.gateway.domain.registry.service;

import com.kevin.gateway.application.IRegistryManageService;
import com.kevin.gateway.domain.registry.model.vo.ApplicationInterfaceMethodVO;
import com.kevin.gateway.domain.registry.model.vo.ApplicationInterfaceVO;
import com.kevin.gateway.domain.registry.model.vo.ApplicationSystemVO;
import com.kevin.gateway.domain.registry.repository.IRegistryManageRepository;
import com.kevin.gateway.infrustructs.po.ApplicationInterface;
import com.kevin.gateway.infrustructs.po.ApplicationInterfaceMethod;
import com.kevin.gateway.infrustructs.po.ApplicationSystem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2024-01-02-18:56
 */
@Service
public class RegistryManageServiceImpl implements IRegistryManageService {
    @Resource
    IRegistryManageRepository registryManageRepository;


    @Override
    public void registryApplication(ApplicationSystemVO applicationSystemVO) {
        ApplicationSystem applicationSystem = ApplicationSystemVO.convertToApplicationSystem(applicationSystemVO);
        registryManageRepository.registryApplicationSystem(applicationSystem);
    }

    @Override
    public void registryApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO) {
        ApplicationInterface applicationInterface = ApplicationInterfaceVO.convertToApplicationInterface(applicationInterfaceVO);
        registryManageRepository.registryApplicationInterface(applicationInterface);
    }

    @Override
    public void registryApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO) {
        ApplicationInterfaceMethod applicationInterfaceMethod = ApplicationInterfaceMethodVO.convertToApplicationInterfaceMethod(applicationInterfaceMethodVO);
        registryManageRepository.registryApplicationInterfaceMethod(applicationInterfaceMethod);
    }
}
