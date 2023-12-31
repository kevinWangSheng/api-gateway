package org.kevin;

import io.netty.channel.Channel;
import org.junit.Test;
import org.kevin.gateway.mapping.HttpCommandType;
import org.kevin.gateway.mapping.HttpStatement;
import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.session.defaults.DefaultGatewaySessionFactory;
import org.kevin.gateway.socket.GatewaySocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author wang
 * @create 2023-12-28-16:43
 */
public class TestStartServer {

    private static final Logger logger = LoggerFactory.getLogger(TestStartServer.class);
    @Test
    public void testStart() throws InterruptedException, ExecutionException {
        GatewaySocketServer server = new GatewaySocketServer();

        Channel channel = Executors.newFixedThreadPool(2).submit(server).get();
        if(null == channel) {
            return;
        }
        while (!channel.isActive()){
            logger.info("the server is startting");
            Thread.sleep(500);
        }
        logger.info("the server is started");
        Thread.sleep(Integer.MAX_VALUE);
    }

//    @Test
//    public void testGenericInvoke() throws ExecutionException, InterruptedException {
//        Configuration configuration = new Configuration();
//        configuration.addReference("api-gateway-test", "com.kevin.gateway.rpc.IActivityBooth", "sayHi");
//
//        GenericReferenceSessionFactoryBuilder sessionFactory = new GenericReferenceSessionFactoryBuilder();
//        Future<Channel> future = sessionFactory.build(configuration);
//        logger.info("服务启动完成：{}",future.get().id());
//        Thread.sleep(Integer.MAX_VALUE);
//    }

//    @Test
//    public void testRefactor() throws InterruptedException, ExecutionException {
//        Configuration configuration = new Configuration();
//        HttpStatement httpStatement = new HttpStatement(
//                "/wg/activity/sayHi",
//                "com.kevin.gateway.rpc.IActivityBooth",
//                "sayHi",
//                "api-gateway-test",
//                HttpCommandType.GET,
//                "java.lang.String");
//        configuration.addMapper(httpStatement);
//        // 创建工厂帮忙创建
//        DefaultGatewaySessionFactory sessionFactory = new DefaultGatewaySessionFactory(configuration);
//        GatewaySessionServer server = new GatewaySessionServer(sessionFactory);
//
//        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
//
//        Channel channel = future.get();
//
//        if(null == channel) {
//            logger.error("the netty server was start error");
//        }
//        // 启动过程
//        while(!channel.isActive()){
//            logger.info("the server is startting");
//            try{ Thread.sleep(500);} catch(InterruptedException e){ e.printStackTrace();}
//        }
//        logger.info("the server is started");
//
//        Thread.sleep(Integer.MAX_VALUE);
//    }

//    @Test
//    public void testHttp() throws InterruptedException, ExecutionException {
//        Configuration configuration = new Configuration();
//        HttpStatement httpStatement01 = new HttpStatement(
//                "/wg/activity/sayHi",
//                "com.kevin.gateway.rpc.IActivityBooth",
//                "sayHi",
//                "api-gateway-test",
//                HttpCommandType.GET,
//                "java.lang.String");
//
//        HttpStatement httpStatement02 = new HttpStatement(
//                "/wg/activity/insert",
//                "com.kevin.gateway.rpc.IActivityBooth",
//                "insert",
//                "api-gateway-test",
//                HttpCommandType.POST,"com.kevin.gateway.rpc.dto.XReq");
//        configuration.addMapper(httpStatement01);
//        configuration.addMapper(httpStatement02);
//
//        // 创建工厂帮忙创建
//        DefaultGatewaySessionFactory sessionFactory = new DefaultGatewaySessionFactory(configuration);
//        GatewaySessionServer server = new GatewaySessionServer(sessionFactory);
//
//        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
//
//        Channel channel = future.get();
//
//        if(null == channel) {
//            logger.error("the netty server was start error");
//        }
//        // 启动过程
//        while(!channel.isActive()){
//            logger.info("the server is startting");
//            try{ Thread.sleep(500);} catch(InterruptedException e){ e.printStackTrace();}
//        }
//        logger.info("the server is started");
//
//        Thread.sleep(Integer.MAX_VALUE);
//    }

    @Test
    public void test_gateway() throws InterruptedException, ExecutionException {
        // 1. 创建配置信息加载注册
        Configuration configuration = new Configuration();
        configuration.registryConfig("api-gateway-test", "zookeeper://124.221.25.145:2181", "com.kevin.gateway.rpc.IActivityBooth", "1.0.0");

        HttpStatement httpStatement01 = new HttpStatement(
                "/wg/activity/sayHi",
                "com.kevin.gateway.rpc.IActivityBooth",
                "sayHi",
                "api-gateway-test",
                HttpCommandType.GET,
                "java.lang.String",
                false);

        HttpStatement httpStatement02 = new HttpStatement(
                "/wg/activity/insert",
                "com.kevin.gateway.rpc.IActivityBooth",
                "insert",
                "api-gateway-test",
                HttpCommandType.POST,
                "com.kevin.gateway.rpc.dto.XReq",
                true);

        configuration.addMapper(httpStatement01);
        configuration.addMapper(httpStatement02);

        // 2. 基于配置构建会话工厂
        DefaultGatewaySessionFactory gatewaySessionFactory = new DefaultGatewaySessionFactory(configuration);

        // 3. 创建启动网关网络服务
        GatewaySocketServer server = new GatewaySocketServer(gatewaySessionFactory, configuration);

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();

        if (null == channel) throw new RuntimeException("netty server start error channel is null");

        while (!channel.isActive()) {
            logger.info("netty server gateway start Ing ...");
            Thread.sleep(500);
        }
        logger.info("netty server gateway start Done! {}", channel.localAddress());

        Thread.sleep(Long.MAX_VALUE);
    }
}
