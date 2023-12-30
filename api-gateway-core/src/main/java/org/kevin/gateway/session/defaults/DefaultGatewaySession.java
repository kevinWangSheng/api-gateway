package org.kevin.gateway.session.defaults;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.kevin.gateway.bind.IGenericReference;
import org.kevin.gateway.datasource.Connection;
import org.kevin.gateway.datasource.DataSource;
import org.kevin.gateway.executor.Executor;
import org.kevin.gateway.mapping.HttpStatement;
import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.session.GatewaySession;
import org.kevin.gateway.type.SimpleTypeRegistry;

import java.util.Map;

/**默认网关会话实现
 * @author wang
 * @create 2023-12-29-18:58
 */
public class DefaultGatewaySession implements GatewaySession {
    private Configuration configuration;

    private Executor executor;

    private String uri;

    public DefaultGatewaySession(Configuration configuration, Executor executor, String uri) {
        this.configuration = configuration;
        this.executor = executor;
        this.uri = uri;
    }

    @Override
    public Object get(String methodName, Map<String,Object> params) {

        HttpStatement httpStatement = configuration.getHttpStatement(uri);
        return executor.exec(httpStatement,params);
    }

    @Override
    public Object post(String methodName, Map<String, Object> params) {
        return get(methodName, params);
    }

    @Override
    public IGenericReference getMapper() {
        return configuration.getMapper(uri,this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
