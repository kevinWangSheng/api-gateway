package com.kevin.gateway.application;

import java.util.Map;

/**
 * @author wang
 * @create 2024-01-06-15:30
 */
public interface IMessageService {
    Map<String,String> queryRedisConfig();

    void pushMessage(String gatewayId,Object message);
}
