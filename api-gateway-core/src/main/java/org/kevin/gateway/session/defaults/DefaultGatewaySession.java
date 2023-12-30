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
import org.kevin.gateway.mapping.HttpStatement;
import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.session.GatewaySession;

/**默认网关会话实现
 * @author wang
 * @create 2023-12-29-18:58
 */
public class DefaultGatewaySession implements GatewaySession {
    private Configuration configuration;

    private DataSource dataSource;

    private String uri;

    public DefaultGatewaySession(Configuration configuration, DataSource dataSource, String uri) {
        this.configuration = configuration;
        this.dataSource = dataSource;
        this.uri = uri;
    }

    @Override
    public Object get(String methodName, Object args) {
        Connection connection = dataSource.getConnection();
        return connection.execute(methodName, new String[]{"java.lang.String"}, new String[]{"str"}, new Object[]{args});
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
