package org.kevin.gateway.socket.handlers;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.dubbo.common.utils.StringUtils;
import org.kevin.gateway.mapping.HttpStatement;
import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.socket.BaseHandler;
import org.kevin.gateway.socket.aggrement.AggrementConstance;
import org.kevin.gateway.socket.aggrement.GatewayResultMessage;
import org.kevin.gateway.socket.aggrement.ResponseParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wang
 * @create 2023-12-31-15:31
 */
public class AuthorizationHandler extends BaseHandler<FullHttpRequest> {
    private Configuration configuration;

    private final Logger logger = LoggerFactory.getLogger(AuthorizationHandler.class);

    public AuthorizationHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void session(ChannelHandlerContext ctx, Channel channel, FullHttpRequest request) {
        logger.info("网关接收请求【鉴权】 uri：{} method：{}", request.uri(), request.method());
        try {
            HttpStatement httpStatement = channel.attr(AggrementConstance.HTTP_STATEMENT).get();
            boolean auth = httpStatement.isAuth();
            if(auth){
                try {
                    String uid = request.headers().get("uId");
                    String token = request.headers().get("token");
                    if(StringUtils.isBlank(token)){
                        DefaultFullHttpResponse response = new ResponseParse().parse(GatewayResultMessage.buildFail(AggrementConstance.ResponseCode._400.getCode(), "请先登录"));
                        channel.writeAndFlush(response);
                        return;
                    }
                    boolean status = configuration.authValidata(uid, token);
                    // 验证成功
                    if(status){
                        request.retain();
                        ctx.fireChannelRead(request);
                        // 鉴权失败
                    }else{
                        DefaultFullHttpResponse response = new ResponseParse().parse(GatewayResultMessage.buildFail(AggrementConstance.ResponseCode._403.getCode(), "对不起你无权访问"));
                        channel.writeAndFlush(response);
                    }
                } catch (Exception e) {
                    // 出现异常
                    DefaultFullHttpResponse response = new ResponseParse().parse(GatewayResultMessage.buildFail(AggrementConstance.ResponseCode._403.getCode(), "对不起，你的鉴权不合法"));
                    channel.writeAndFlush(response);
                }
            }else{
                // 不需要鉴权的话直接放行
                request.retain();
                ctx.fireChannelRead(request);
            }
        } catch (Exception e) {
            DefaultFullHttpResponse response = new ResponseParse().parse(GatewayResultMessage.buildFail(AggrementConstance.ResponseCode._500.getCode(), "网络协议调用失败"));
            channel.writeAndFlush(response);
        }
    }
}
