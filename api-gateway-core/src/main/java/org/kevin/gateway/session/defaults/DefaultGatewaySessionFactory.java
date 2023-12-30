package org.kevin.gateway.session.defaults;

import org.kevin.gateway.datasource.DataSource;
import org.kevin.gateway.datasource.DataSourceFactory;
import org.kevin.gateway.datasource.unpooled.UnpooledDataSourceFactory;
import org.kevin.gateway.executor.Executor;
import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.session.GatewaySession;
import org.kevin.gateway.session.GatewaySessionFactory;

/**
 * @author wang
 * @create 2023-12-29-19:12
 */
public class DefaultGatewaySessionFactory implements GatewaySessionFactory {
    private Configuration configuration;

    public DefaultGatewaySessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public GatewaySession openSession(String uri) {
        DataSourceFactory dataSourceFactory = new UnpooledDataSourceFactory();
        dataSourceFactory.setProperties(configuration,uri);
        DataSource dataSource = dataSourceFactory.getDataSource();

        Executor executor = configuration.newExecutor(dataSource.getConnection());
        return new DefaultGatewaySession(configuration,executor,uri);
    }
}
