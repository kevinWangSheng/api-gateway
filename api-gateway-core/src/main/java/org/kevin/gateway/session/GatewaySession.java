package org.kevin.gateway.session;

import org.kevin.gateway.bind.IGenericReference;

/**
 * @author wang
 * @create 2023-12-29-17:51
 */
public interface GatewaySession {
    Object get(String uri,Object args);

    IGenericReference getMapper(String uri);

    Configuration getConfiguration();
}
