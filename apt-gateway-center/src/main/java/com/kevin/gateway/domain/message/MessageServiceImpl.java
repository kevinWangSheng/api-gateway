package com.kevin.gateway.domain.message;

import com.kevin.gateway.application.IMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @create 2024-01-06-15:32
 */
@Service
public class MessageServiceImpl implements IMessageService{
    @Resource
    private Publisher publisher;

    @Value("124.221.25.145")
    private String host;

    @Value("6379")
    private Integer port;

    @Override
    public Map<String, String> queryRedisConfig() {
        return new HashMap<>(){{
            put("host",host);
            put("port",String.valueOf(port));
        }};
    }

    @Override
    public void pushMessage(String gatewayId, Object message) {
        publisher.pushMessage(gatewayId,message);
    }
}
