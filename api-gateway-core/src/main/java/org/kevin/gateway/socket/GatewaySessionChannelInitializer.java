package org.kevin.gateway.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.session.GatewaySessionFactory;
import org.kevin.gateway.socket.handlers.SessionServerHandler;

/**
 * @author wang
 * @create 2023-12-28-16:33
 */
public class GatewaySessionChannelInitializer extends ChannelInitializer<SocketChannel> {

    private GatewaySessionFactory sessionFactory;

    public GatewaySessionChannelInitializer(GatewaySessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new HttpRequestDecoder());
        socketChannel.pipeline().addLast(new HttpResponseEncoder());
        socketChannel.pipeline().addLast(new HttpObjectAggregator(1024*1024));
        socketChannel.pipeline().addLast(new SessionServerHandler(sessionFactory));
    }
}
