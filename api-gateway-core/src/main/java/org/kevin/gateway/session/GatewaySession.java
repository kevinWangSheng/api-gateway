package org.kevin.gateway.session;

import org.kevin.gateway.bind.IGenericReference;

import java.util.Map;

/**
 * @author wang
 * @create 2023-12-29-17:51
 */
public interface GatewaySession {
    Object get(String methodName,Map<String,Object> params);

    IGenericReference getMapper();

    Configuration getConfiguration();

    Object post(String methodName, Map<String,Object> params);
}
