package org.kevin;

import io.netty.channel.Channel;
import org.junit.Test;
import org.kevin.gateway.session.SessionServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

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
}
