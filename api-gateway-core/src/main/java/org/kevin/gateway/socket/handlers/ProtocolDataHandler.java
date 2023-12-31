package org.kevin.gateway.socket.handlers;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import org.kevin.gateway.bind.IGenericReference;
import org.kevin.gateway.executor.result.SessionResult;
import org.kevin.gateway.session.GatewaySession;
import org.kevin.gateway.session.defaults.DefaultGatewaySessionFactory;
import org.kevin.gateway.socket.BaseHandler;
import org.kevin.gateway.socket.aggrement.AggrementConstance;
import org.kevin.gateway.socket.aggrement.GatewayResultMessage;
import org.kevin.gateway.socket.aggrement.RequestParser;
import org.kevin.gateway.socket.aggrement.ResponseParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author wang
 * @create 2023-12-31-14:57
 */
public class ProtocolDataHandler extends BaseHandler<FullHttpRequest> {

    private final Logger logger = LoggerFactory.getLogger(ProtocolDataHandler.class);
    private final DefaultGatewaySessionFactory sessionFactory;

    public ProtocolDataHandler(DefaultGatewaySessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected void session(ChannelHandlerContext ctx, Channel channel, FullHttpRequest request) {
        try {
            RequestParser requestParser = new RequestParser(request);
            String uri = requestParser.getUri();
            if(uri == null) return;
            Map<String, Object> args = requestParser.parese();

            // 获取公共会话并进行调用
            GatewaySession gatewaySession = sessionFactory.openSession(uri);
            IGenericReference genericReference = gatewaySession.getMapper();
            SessionResult result = genericReference.$invoke(args);

            // 构建回应
            DefaultFullHttpResponse response = new ResponseParse().parse(("0000".equals(result.getCode()) ? GatewayResultMessage.buildSuccess(result.getData()) : GatewayResultMessage.buildFail(AggrementConstance.ResponseCode._404.getCode(), "网络协议调用失败")));
            channel.writeAndFlush(response);
        } catch (Exception e){
            DefaultFullHttpResponse response = new ResponseParse().parse(GatewayResultMessage.buildFail(AggrementConstance.ResponseCode._500.getCode(), "网络协议调用失败"));
            channel.writeAndFlush(response);
        }
    }
}
