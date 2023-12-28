package org.kevin.gateway.session;

import io.netty.channel.Channel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author wang
 * @create 2023-12-28-21:28
 */
public interface IGenericReferenceSessionFactory {
    Future<Channel> openSession() throws InterruptedException, ExecutionException;
}
