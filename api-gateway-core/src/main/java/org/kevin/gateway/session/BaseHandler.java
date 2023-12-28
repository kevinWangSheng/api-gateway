package org.kevin.gateway.session;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**数据处理的抽象类--基础类
 * @author wang
 * @create 2023-12-28-16:07
 */
public abstract class BaseHandler<T> extends SimpleChannelInboundHandler<T> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, T t) throws Exception {
        session(channelHandlerContext,channelHandlerContext.channel(),t);
    }

    protected abstract void session(ChannelHandlerContext ctx, Channel channel,T request);
}
