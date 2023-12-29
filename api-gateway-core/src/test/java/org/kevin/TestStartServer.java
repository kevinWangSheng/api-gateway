package org.kevin;

import io.netty.channel.Channel;
import org.junit.Test;
import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.session.GenericReferenceSessionFactoryBuilder;
import org.kevin.gateway.session.IGenericReferenceSessionFactory;
import org.kevin.gateway.session.SessionServer;
import org.kevin.gateway.session.defaults.GenericReferenceSessionFactory;
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
        SessionServer server = new SessionServer();

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

    @Test
    public void testGenericInvoke() throws ExecutionException, InterruptedException {
        Configuration configuration = new Configuration();
        configuration.addReference("api-gateway-test", "com.kevin.gateway.rpc.IActivityBooth", "sayHi");

        GenericReferenceSessionFactoryBuilder sessionFactory = new GenericReferenceSessionFactoryBuilder();
        Future<Channel> future = sessionFactory.build(configuration);
        logger.info("服务启动完成：{}",future.get().id());
        Thread.sleep(Integer.MAX_VALUE);
    }
}
