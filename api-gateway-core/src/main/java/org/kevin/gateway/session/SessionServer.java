package org.kevin.gateway.session;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

/**
 * @author wang
 * @create 2023-12-28-16:35
 */
public class SessionServer implements Callable<Channel> {

    private static final Logger logger = LoggerFactory.getLogger(SessionServer.class);

    private Configuration configuration;
    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup work = new NioEventLoopGroup();

    private Channel channel;

    private final int port = 6789;

    public SessionServer(Configuration configuration) {
        this.configuration = configuration;
    }

    public SessionServer() {
    }

    @Override
    public Channel call() throws Exception {
        ServerBootstrap bs = new ServerBootstrap();
        ChannelFuture future = null;
        try {
            bs.group(boss,work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childHandler(new SessionChannelInitializer(configuration));

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
