package org.kevin.gateway.session;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.kevin.gateway.authorization.IAuth;
import org.kevin.gateway.authorization.auth.AuthService;
import org.kevin.gateway.mapping.HttpStatement;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @create 2024-01-08-14:28
 */
@Component
public class ConfigurationRedis {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    private String host = "127.0.0.1";

    private Integer port = 6789;

    private int bossNThreads = 1;

    private int workNThreads = 4;

    private final String APPLICATION_HASH_KEY = "gateway-application-key";

    private final String REGISTRY_HASH_KEY = "gateway-registry-key";

    private final String REFERENCE_HASH_KEY = "gateway-reference-key";


    private final Map<String, HttpStatement> httpStatementMap = new HashMap<>();

    private final IAuth auth = new AuthService();

    public synchronized void registryConfig(String application, String address, String interfaceName, String interfaceVersion){
        if(redisTemplate.opsForHash().get(APPLICATION_HASH_KEY,application) == null) {
            ApplicationConfig applicationConfig = new ApplicationConfig();
            applicationConfig.setName(application);
            applicationConfig.setQosEnable(false);
            redisTemplate.opsForHash().put(APPLICATION_HASH_KEY,application,applicationConfig);
        }

        if(redisTemplate.opsForHash().get(REGISTRY_HASH_KEY,application) == null){
            RegistryConfig registryConfig = new RegistryConfig();
            registryConfig.setAddress(address);
            registryConfig.setRegister(false);
            redisTemplate.opsForHash().put(REGISTRY_HASH_KEY,application,registryConfig);
        }

        if(redisTemplate.opsForHash().get(REFERENCE_HASH_KEY,interfaceName) == null){
            ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
            referenceConfig.setInterface(interfaceName);
            referenceConfig.setVersion(interfaceVersion);
            referenceConfig.setGeneric("true");
            redisTemplate.opsForHash().put(REFERENCE_HASH_KEY,interfaceName,referenceConfig);
        }
    }

    public HttpStatement getHttpStatement(String uri){
        return httpStatementMap.get(uri);
    }

    public void addHttpStatement(HttpStatement statement) {
        httpStatementMap.put(statement.getUri(),statement);
    }


    public boolean authValidata(String id,String token){
        return auth.validate(id,token);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public int getBossNThreads() {
        return bossNThreads;
    }

    public void setBossNThreads(int bossNThreads) {
        this.bossNThreads = bossNThreads;
    }

    public int getWorkNThreads() {
        return workNThreads;
    }

    public void setWorkNThreads(int workNThreads) {
        this.workNThreads = workNThreads;
    }
}
