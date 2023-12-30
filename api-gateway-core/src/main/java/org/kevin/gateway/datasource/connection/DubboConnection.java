package org.kevin.gateway.datasource.connection;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.kevin.gateway.datasource.Connection;

/**
 * @author wang
 * @create 2023-12-30-14:14
 */
public class DubboConnection implements Connection {
    private final GenericService genericService;


    public DubboConnection(ApplicationConfig applicationConfig, RegistryConfig registryConfig, ReferenceConfig<GenericService> referenceConfig){
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(applicationConfig).registry(registryConfig).reference(referenceConfig).start();

        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        genericService = cache.get(referenceConfig);
    }

    @Override
    public Object execute(String method, String[] paramterTypes, String[] paramterNames, Object[] args) {
        return genericService.$invoke(method,paramterTypes,args);
    }
}
