package org.kevin.gateway.bind;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.kevin.gateway.session.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @create 2023-12-28-21:46
 */
public class GenericReferenceRegistry {
    private final Configuration configuration;


    public GenericReferenceRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    public final Map<String, GenericReferenceProxyFactory> knownGenericReference = new HashMap<>();

    public IGenericReference getGenericReference(String methodName){
        GenericReferenceProxyFactory genericReferenceProxyFactory = knownGenericReference.get(methodName);
        if(null == genericReferenceProxyFactory){
            throw new RuntimeException("type "+methodName+" is not know to GenericReferenceRegistry");
        }
        return genericReferenceProxyFactory.getInstance(methodName);
    }

    // 这里根据对应的应用名称，接口名称和方法名称自己构建一个dubbo服务的启动
    public void addGenericReference(String application,String interfaceName,String methodName){
        // 获取对应的配置信息，包括程序，注册，以及泛化接口
        ApplicationConfig applicationConfig = configuration.getApplicationConfig(application);
        RegistryConfig registryConfig = configuration.getRegistryConfig(application);
        ReferenceConfig<GenericService> referenceConfig = configuration.getReferenceConfig(interfaceName);

        // 构建dubbo服务
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(applicationConfig).registry(registryConfig).reference(referenceConfig).start();

        //获取泛化接口
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(referenceConfig);

        //将服务放到工厂中，这里已经是通过接口名称去进行定位，获取对应的泛化服务，调用对应的接口
        knownGenericReference.put(methodName,new GenericReferenceProxyFactory(genericService));
    }
}
