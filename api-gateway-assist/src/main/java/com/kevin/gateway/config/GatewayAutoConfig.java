package com.kevin.gateway.config;

import com.kevin.gateway.application.GatewayApplication;
import com.kevin.gateway.domain.service.GatewayCenterService;
import io.netty.channel.Channel;
import org.kevin.gateway.session.defaults.DefaultGatewaySessionFactory;
import org.kevin.gateway.socket.GatewaySocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author wang
 * @create 2024-01-02-21:25
 */
@EnableConfigurationProperties(GatewayServiceProperties.class)
@Configuration
public class GatewayAutoConfig {
    private static final Logger logger = LoggerFactory.getLogger(GatewayAutoConfig.class);

    @Bean
    public GatewayCenterService registryGatewayService(){
        return new GatewayCenterService();
    }

    @Bean
    public GatewayApplication gatewayApplication(GatewayServiceProperties properties, GatewayCenterService registryGatewayService, org.kevin.gateway.session.Configuration gatewayConfiguration,Channel gatewaySocketServerChannel){
        return new GatewayApplication(properties, registryGatewayService,gatewayConfiguration,gatewaySocketServerChannel);
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(GatewayServiceProperties properties, GatewayCenterService gatewayCenterService){
        // 从注册中心拉取Redis配置信息
        Map<String, String> redisConfig = gatewayCenterService.queryRedisConfig(properties.getAddress());
        // 构建Redis服务
        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
        standaloneConfiguration.setHostName(redisConfig.get("host"));
        standaloneConfiguration.setPort(Integer.parseInt(redisConfig.get("port")));
        standaloneConfiguration.setPassword(redisConfig.get("password"));

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(100);
        poolConfig.setMaxWaitMillis(30 *1000);
        poolConfig.setMinIdle(20);
        poolConfig.setMaxIdle(40);
        poolConfig.setTestWhileIdle(true);

        JedisClientConfiguration client = JedisClientConfiguration.builder()
                .connectTimeout(Duration.ofSeconds(2))
                .clientName("api-gateway-assist-redis-" + properties.getGatewayId())
                .usePooling().poolConfig(poolConfig).build();

        return new JedisConnectionFactory(standaloneConfiguration, client);
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(GatewayApplication gatewayApplication){
        return new MessageListenerAdapter(gatewayApplication,"receiveMessage");
    }

    @Bean
    public RedisMessageListenerContainer container(GatewayServiceProperties properties, RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter adapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);

        container.addMessageListener(adapter,new PatternTopic(properties.getGatewayId()));
        return container;
    }

    @Bean
    public org.kevin.gateway.session.Configuration gatewayConfiguration(GatewayServiceProperties properties){
        org.kevin.gateway.session.Configuration configuration = new org.kevin.gateway.session.Configuration();
        String[] split = properties.getGatewayAddress().split(":");
        configuration.setHost(split[0].trim());
        configuration.setPort(Integer.parseInt(split[1].trim()));
        return configuration;
    }

    @Bean("gatewaySocketServerChannel")
    public Channel initGateway(org.kevin.gateway.session.Configuration configuration){
        DefaultGatewaySessionFactory sessionFactory = new DefaultGatewaySessionFactory(configuration);

        GatewaySocketServer server = new GatewaySocketServer(sessionFactory,configuration);

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = null;
        try {
            channel = future.get();
        } catch (Exception e) {
            throw new RuntimeException("start the netty server was failure:"+e.getMessage());
        }
        if(null == channel){
            throw new RuntimeException("api-gateway-core start the netty server was failure");
        }
        while(!channel.isActive()){
            logger.info("the server was startting...");
            try{ Thread.sleep(500);} catch(InterruptedException e){ e.printStackTrace();}
        }
        logger.info("the netty server was start successful,address:{}!",channel.localAddress());
        return channel;
    }


}
