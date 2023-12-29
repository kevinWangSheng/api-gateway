package org.kevin.gateway.bind;

import org.kevin.gateway.mapping.HttpStatement;
import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.session.GatewaySession;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @create 2023-12-29-18:46
 */
public class MapperRegistry {
    private Configuration configuration;

    public final Map<String, MapperProxyFactory> knownGenericReference = new HashMap<>();

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    public IGenericReference getMapper(String uri, GatewaySession gatewaySession){
        MapperProxyFactory mapperProxyFactory = knownGenericReference.get(uri);
        if(null == mapperProxyFactory){
            throw new RuntimeException("error，对应的uri没有代理请求工厂创建");
        }
        return mapperProxyFactory.newInstance(gatewaySession);
    }

    public void addMapper(HttpStatement statement){
        String uri = statement.getUri();
        if(null == uri){
            throw new RuntimeException("uri不能为空");
        }
        if(hasMapper(uri)){
            throw new RuntimeException("已经有对应的uri匹配方法了，不能创建多个");
        }
        // 添加对应的代理工厂。
        knownGenericReference.put(uri,new MapperProxyFactory(uri));
        configuration.addHttpStatement(statement);
    }

    public boolean hasMapper(String uri){
        return knownGenericReference.containsKey(uri);
    }
}
