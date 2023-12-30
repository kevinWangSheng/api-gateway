package org.kevin.gateway.datasource;

/**
 * @author wang
 * @create 2023-12-30-14:07
 */
public interface Connection {
    Object execute(String method,String[] paramterTypes,String[] paramterNames,Object[] args);
}
