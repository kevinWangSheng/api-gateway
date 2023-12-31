package org.kevin.gateway.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultChannelId;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.session.GatewaySessionFactory;
import org.kevin.gateway.session.defaults.DefaultGatewaySessionFactory;
import org.kevin.gateway.socket.handlers.AuthorizationHandler;
import org.kevin.gateway.socket.handlers.ProtocolDataHandler;
import org.kevin.gateway.socket.handlers.SessionServerHandler;

/**
 * @author wang
 * @create 2023-12-28-16:33
 */
public class GatewaySessionChannelInitializer extends ChannelInitializer<SocketChannel> {

    private Configuration configuration;
    private DefaultGatewaySessionFactory sessionFactory;

    public GatewaySessionChannelInitializer(Configuration configuration, DefaultGatewaySessionFactory sessionFactory) {
        this.configuration = configuration;
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpRequestDecoder());
        pipeline.addLast(new HttpResponseEncoder());
        pipeline.addLast(new HttpObjectAggregator(1024*1024));
        // 会话前处理逻辑
        pipeline.addLast(new SessionServerHandler(configuration));
        // 授权逻辑处理逻辑
        pipeline.addLast(new AuthorizationHandler(configuration));
        // 协议数据处理逻辑，调用具体的协议处理逻辑，并返回结果给前端。
        pipeline.addLast(new ProtocolDataHandler(sessionFactory));
    }
}
