package org.kevin.gateway.datasource;

import org.kevin.gateway.session.Configuration;

/**
 * @author wang
 * @create 2023-12-30-14:06
 */
public interface DataSourceFactory {
    void setProperties(Configuration configuration,String uri);
    DataSource getDataSource();
}
