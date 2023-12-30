package org.kevin.gateway.session;

import io.netty.channel.Channel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author wang
 * @create 2023-12-29-19:11
 */
public interface GatewaySessionFactory {
    GatewaySession openSession(String uri);
}
