package com.kevin.gateway.domain.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2024-01-06-15:39
 */
@Service
public class Publisher {

    private final RedisTemplate<String, Object> redisMessageTemplate;


    @Autowired
    public Publisher(RedisTemplate<String, Object> redisMessageTemplate) {
        this.redisMessageTemplate = redisMessageTemplate;
    }

    public void pushMessage(String gatewayId, Object message) {
        redisMessageTemplate.convertAndSend(gatewayId, message);
    }
}
