package org.kevin;

import io.netty.channel.Channel;
import org.junit.Test;
import org.kevin.gateway.mapping.HttpCommandType;
import org.kevin.gateway.mapping.HttpStatement;
import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.session.GatewaySessionFactory;
import org.kevin.gateway.session.defaults.DefaultGatewaySessionFactory;
import org.kevin.gateway.socket.GatewaySessionServer;
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
        GatewaySessionServer server = new GatewaySessionServer();

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

    @Test
    public void testRefactor() throws InterruptedException, ExecutionException {
        Configuration configuration = new Configuration();
        HttpStatement httpStatement = new HttpStatement(
                "/wg/activity/sayHi",
                "com.kevin.gateway.rpc.IActivityBooth",
                "sayHi",
                "api-gateway-test",
                HttpCommandType.GET,
                "java.lang.String");
        configuration.addMapper(httpStatement);
        // 创建工厂帮忙创建
        DefaultGatewaySessionFactory sessionFactory = new DefaultGatewaySessionFactory(configuration);
        GatewaySessionServer server = new GatewaySessionServer(sessionFactory);

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);

        Channel channel = future.get();

        if(null == channel) {
            logger.error("the netty server was start error");
        }
        // 启动过程
        while(!channel.isActive()){
            logger.info("the server is startting");
            try{ Thread.sleep(500);} catch(InterruptedException e){ e.printStackTrace();}
        }
        logger.info("the server is started");

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void testHttp() throws InterruptedException, ExecutionException {
        Configuration configuration = new Configuration();
        HttpStatement httpStatement01 = new HttpStatement(
                "/wg/activity/sayHi",
                "com.kevin.gateway.rpc.IActivityBooth",
                "sayHi",
                "api-gateway-test",
                HttpCommandType.GET,
                "java.lang.String");

        HttpStatement httpStatement02 = new HttpStatement(
                "/wg/activity/insert",
                "com.kevin.gateway.rpc.IActivityBooth",
                "insert",
                "api-gateway-test",
                HttpCommandType.POST,"com.kevin.gateway.rpc.dto.XReq");
        configuration.addMapper(httpStatement01);
        configuration.addMapper(httpStatement02);

        // 创建工厂帮忙创建
        DefaultGatewaySessionFactory sessionFactory = new DefaultGatewaySessionFactory(configuration);
        GatewaySessionServer server = new GatewaySessionServer(sessionFactory);

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);

        Channel channel = future.get();

        if(null == channel) {
            logger.error("the netty server was start error");
        }
        // 启动过程
        while(!channel.isActive()){
            logger.info("the server is startting");
            try{ Thread.sleep(500);} catch(InterruptedException e){ e.printStackTrace();}
        }
        logger.info("the server is started");

        Thread.sleep(Integer.MAX_VALUE);
    }
}
