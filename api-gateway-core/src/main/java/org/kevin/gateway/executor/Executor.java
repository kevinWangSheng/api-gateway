package org.kevin.gateway.executor;

import org.kevin.gateway.executor.result.GatewayResult;
import org.kevin.gateway.mapping.HttpStatement;

import java.util.Map;

/**
 * @author wang
 * @create 2023-12-30-22:01
 */
public interface Executor {
    GatewayResult exec(HttpStatement httpStatement, Map<String,Object> params);
}
