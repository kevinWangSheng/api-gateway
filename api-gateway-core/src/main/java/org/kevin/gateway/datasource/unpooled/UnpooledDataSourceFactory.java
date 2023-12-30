package org.kevin.gateway.datasource.unpooled;

import org.kevin.gateway.datasource.DataSource;
import org.kevin.gateway.datasource.DataSourceFactory;
import org.kevin.gateway.datasource.DataSourceType;
import org.kevin.gateway.session.Configuration;

/**
 * @author wang
 * @create 2023-12-30-14:08
 */
public class UnpooledDataSourceFactory implements DataSourceFactory {
    protected UnpooledDataSource dataSource;

    public UnpooledDataSourceFactory() {
        this.dataSource = new UnpooledDataSource();
    }

    @Override
    public void setProperties(Configuration configuration, String uri) {
        dataSource.setDataSourceType(DataSourceType.Dubbo);
        dataSource.setHttpStatement(configuration.getHttpStatement(uri));
        dataSource.setConfiguration(configuration);
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }
}
