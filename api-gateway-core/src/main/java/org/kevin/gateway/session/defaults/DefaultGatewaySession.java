package org.kevin.gateway.session.defaults;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.kevin.gateway.bind.IGenericReference;
import org.kevin.gateway.mapping.HttpStatement;
import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.session.GatewaySession;

/**默认网关会话实现
 * @author wang
 * @create 2023-12-29-18:58
 */
public class DefaultGatewaySession implements GatewaySession {
    private Configuration configuration;

    public DefaultGatewaySession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Object get(String uri, Object args) {
        HttpStatement httpStatement = configuration.getHttpStatement(uri);
        String application = httpStatement.getApplication();
        String interfaceName = httpStatement.getInterfaceName();

        ApplicationConfig applicationConfig = configuration.getApplicationConfig(application);
        ReferenceConfig<GenericService> referenceConfig = configuration.getReferenceConfig(interfaceName);
        RegistryConfig registryConfig = configuration.getRegistryConfig(application);

        // 构建dubbo服务
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(applicationConfig).registry(registryConfig).reference(referenceConfig).start();

        //进行泛化调用
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(referenceConfig);
        return genericService.$invoke(httpStatement.getMethodName(), new String[]{"java.lang.String"}, new Object[]{"wangwu"});
    }

    @Override
    public IGenericReference getMapper(String uri) {
        return configuration.getMapper(uri,this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
