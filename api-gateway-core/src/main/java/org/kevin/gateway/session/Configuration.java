package org.kevin.gateway.session;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.kevin.gateway.bind.GenericReferenceRegistry;
import org.kevin.gateway.bind.IGenericReference;

import java.util.HashMap;
import java.util.Map;

/**基础类配置
 * @author wang
 * @create 2023-12-28-21:13
 */
public class Configuration {
    private final String address = "zookeeper://124.221.25.145:2181";
    public static final String applicationName = "api-gateway-test";

    public static final String activityBoothInterfaceVersion = "1.0.0";

    public static final String activityBoothInterfaceName = "com.kevin.gateway.rpc.IActivityBooth";
    private final GenericReferenceRegistry registryConfig = new GenericReferenceRegistry(this);

    private final Map<String, ApplicationConfig> configMap = new HashMap<>();

    private final Map<String, RegistryConfig> registryConfigMap = new HashMap<>();

    private final Map<String, ReferenceConfig<GenericService>> referenceConfigMap = new HashMap<>();


    public Configuration(){
        ApplicationConfig applicationConfig = new ApplicationConfig();

        applicationConfig.setName(applicationName);
        applicationConfig.setQosEnable(false);
        configMap.put(applicationName,applicationConfig);

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(address);
        registryConfig.setRegister(false);
        registryConfigMap.put(applicationName,registryConfig);

        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setInterface(activityBoothInterfaceName);
        referenceConfig.setVersion(activityBoothInterfaceVersion);
        referenceConfig.setGeneric("true");
        referenceConfigMap.put(activityBoothInterfaceName,referenceConfig);

    }

    public String getAddress() {
        return address;
    }

    public GenericReferenceRegistry getRegistryConfig() {
        return registryConfig;
    }

    public Map<String, ApplicationConfig> getConfigMap() {
        return configMap;
    }

    public Map<String, RegistryConfig> getRegistryConfigMap() {
        return registryConfigMap;
    }

    public Map<String, ReferenceConfig<GenericService>> getReferenceConfigMap() {
        return referenceConfigMap;
    }

    public ApplicationConfig getApplicationConfig(String application){
        return configMap.get(application);
    }


    public RegistryConfig getRegistryConfig(String application){
        return registryConfigMap.get(application);
    }

    public ReferenceConfig<GenericService> getReferenceConfig(String interfaceName){
        return referenceConfigMap.get(interfaceName);
    }

    public IGenericReference getGenericReference(String interfaceName){
        return registryConfig.getGenericReference(interfaceName);
    }

    public void addReference(String application,String interfaceName,String methodName){
        registryConfig.addGenericReference(application,interfaceName,methodName);
    }
}

