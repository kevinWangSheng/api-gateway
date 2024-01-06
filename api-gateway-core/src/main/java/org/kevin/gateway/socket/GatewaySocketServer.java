package org.kevin.gateway.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.session.defaults.DefaultGatewaySessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

/**
 * @author wang
 * @create 2023-12-28-16:35
 */
public class GatewaySocketServer implements Callable<Channel> {

    private static final Logger logger = LoggerFactory.getLogger(GatewaySocketServer.class);

    private DefaultGatewaySessionFactory sessionFactory;

    private Configuration configuration;
    private EventLoopGroup boss;
    private EventLoopGroup work;

    private Channel channel;

    private final int port = 6789;

    public GatewaySocketServer(DefaultGatewaySessionFactory sessionFactory, Configuration configuration) {
        this.sessionFactory = sessionFactory;
        this.configuration = configuration;
        this.initEventWorkLoop();
    }

    public GatewaySocketServer() {
    }

    public void initEventWorkLoop(){
        this.boss = new NioEventLoopGroup(configuration.getBossNThreads());
        this.work = new NioEventLoopGroup(configuration.getWorkNThreads());
    }

    @Override
    public Channel call() throws Exception {
        ServerBootstrap bs = new ServerBootstrap();
        ChannelFuture future = null;
        try {
            bs.group(boss,work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childHandler(new GatewaySessionChannelInitializer(configuration,sessionFactory));

             future = bs.bind(new InetSocketAddress(port)).syncUninterruptibly();
            this.channel = future.channel();
        } catch (Exception e) {
            logger.error("the socket server start occure error");
        } finally {
            if(null != future && future.isSuccess()){
                logger.info("the socket server start success");
                return channel;
            }else{
                logger.error("the socket server start error");
            }
            return channel;
        }
    }
}
