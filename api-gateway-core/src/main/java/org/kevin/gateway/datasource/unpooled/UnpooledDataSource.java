package org.kevin.gateway.datasource.unpooled;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.kevin.gateway.datasource.Connection;
import org.kevin.gateway.datasource.DataSource;
import org.kevin.gateway.datasource.DataSourceType;
import org.kevin.gateway.datasource.connection.DubboConnection;
import org.kevin.gateway.mapping.HttpStatement;
import org.kevin.gateway.session.Configuration;

/**
 * @author wang
 * @create 2023-12-30-14:09
 */
public class UnpooledDataSource implements DataSource {
    private HttpStatement httpStatement;

    private DataSourceType dataSourceType;

    private Configuration configuration;

    public UnpooledDataSource(HttpStatement httpStatement, DataSourceType dataSourceType, Configuration configuration) {
        this.httpStatement = httpStatement;
        this.dataSourceType = dataSourceType;
        this.configuration = configuration;
    }

    public UnpooledDataSource() {
    }

    @Override
    public Connection getConnection() {
        switch (dataSourceType){
            case Dubbo:
                ApplicationConfig applicationConfig = configuration.getApplicationConfig(httpStatement.getApplication());
                RegistryConfig registryConfig = configuration.getRegistryConfig(httpStatement.getApplication());
                ReferenceConfig<GenericService> referenceConfig = configuration.getReferenceConfig(httpStatement.getInterfaceName());
                return new DubboConnection(applicationConfig,registryConfig,referenceConfig);
            case Http:
                break;
            default:
                break;
        }
        throw new RuntimeException("DataSourceType:"+dataSourceType+" 没有对应的数据源实现");
    }

    public HttpStatement getHttpStatement() {
        return httpStatement;
    }

    public void setHttpStatement(HttpStatement httpStatement) {
        this.httpStatement = httpStatement;
    }

    public DataSourceType getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(DataSourceType dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
