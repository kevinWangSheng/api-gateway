package org.kevin.gateway.datasource;

/**
 * @author wang
 * @create 2023-12-30-14:06
 */
public interface DataSource {
    Connection getConnection();
}
