package org.kevin.gateway.session;

import io.netty.channel.Channel;
import org.kevin.gateway.session.defaults.GenericReferenceSessionFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author wang
 * @create 2023-12-28-21:29
 */
public class GenericReferenceSessionFactoryBuilder {
    public Future<Channel> build(Configuration configuration){
        IGenericReferenceSessionFactory sessionFactory = new GenericReferenceSessionFactory(configuration);
        try {
            return sessionFactory.openSession();
        } catch (InterruptedException |ExecutionException  e) {
            throw new RuntimeException(e);
        }
    }
}
