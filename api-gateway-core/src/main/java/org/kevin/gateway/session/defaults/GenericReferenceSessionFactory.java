package org.kevin.gateway.session.defaults;

import io.netty.channel.Channel;
import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.session.IGenericReferenceSessionFactory;
import org.kevin.gateway.session.SessionServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author wang
 * @create 2023-12-28-21:33
 */
public class GenericReferenceSessionFactory implements IGenericReferenceSessionFactory {
    private final Logger logger = LoggerFactory.getLogger(GenericReferenceSessionFactory.class);

    private Configuration configuration;

    public GenericReferenceSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Future<Channel> openSession() throws InterruptedException, ExecutionException {
        SessionServer server = new SessionServer(configuration);

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);

        Channel channel = future.get();
        if(null == channel) throw new RuntimeException("the session server start was error");
        while(!channel.isActive()){
            logger.info("the session server is starting...");
            Thread.sleep(500);
        }
        logger.info("the session server start successfully,{}",channel.localAddress());
        return future;
    }
}
