package org.kevin.gateway.socket.handlers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.kevin.gateway.bind.IGenericReference;
import org.kevin.gateway.executor.result.SessionResult;
import org.kevin.gateway.mapping.HttpStatement;
import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.socket.BaseHandler;
import org.kevin.gateway.session.GatewaySession;
import org.kevin.gateway.session.GatewaySessionFactory;
import org.kevin.gateway.socket.aggrement.AggrementConstance;
import org.kevin.gateway.socket.aggrement.GatewayResultMessage;
import org.kevin.gateway.socket.aggrement.RequestParser;
import org.kevin.gateway.socket.aggrement.ResponseParse;
import org.slf4j.Logger;

import java.util.Map;

/** 主要处理发送过来的请求
 * @author wang
 * @create 2023-12-28-16:10
 */
public class SessionServerHandler extends BaseHandler<FullHttpRequest> {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SessionServerHandler.class);




    public static final String favicon = "favicon.ico";

    private Configuration configuration;

    public SessionServerHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 处理一个会话过来的请求,调用泛化接口，然后将调用结果封装，并将返回信息进行封装处理返回
     * @param ctx
     * @param channel
     * @param request
     */
    @Override
    protected void session(ChannelHandlerContext ctx, Channel channel, FullHttpRequest request) {
        try {
            RequestParser requestParser = new RequestParser(request);
            String uri = requestParser.getUri();

            // 设置信息
            HttpStatement httpStatement = configuration.getHttpStatement(uri);
            channel.attr(AggrementConstance.HTTP_STATEMENT).set(httpStatement);

            //放行服务
            request.retain();
            ctx.fireChannelRead(request);
        } catch (Exception e) {
            DefaultFullHttpResponse response = new ResponseParse().parse(GatewayResultMessage.buildFail(AggrementConstance.ResponseCode._500.getCode(), "网络协议调用失败"));
            channel.writeAndFlush(response);
        }
    }
}
