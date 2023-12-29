package org.kevin.gateway.socket.handlers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.kevin.gateway.bind.IGenericReference;
import org.kevin.gateway.socket.BaseHandler;
import org.kevin.gateway.session.GatewaySession;
import org.kevin.gateway.session.GatewaySessionFactory;
import org.slf4j.Logger;

/** 主要处理发送过来的请求
 * @author wang
 * @create 2023-12-28-16:10
 */
public class SessionServerHandler extends BaseHandler<FullHttpRequest> {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SessionServerHandler.class);




    public static final String favicon = "favicon.ico";

    private GatewaySessionFactory gatewaySessionFactory;

    public SessionServerHandler(GatewaySessionFactory gatewaySessionFactory) {
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    /**
     * 处理一个会话过来的请求,调用泛化接口，然后将调用结果封装，并将返回信息进行封装处理返回
     * @param ctx
     * @param channel
     * @param request
     */
    @Override
    protected void session(ChannelHandlerContext ctx, Channel channel, FullHttpRequest request) {
        String uri = request.uri();
        logger.info("网关接收到请求:uri:{},method:{}", uri,request.method());
        
        //返回处理的信息
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);

        // 返回信息控制：简单处理
        String methodName = uri.substring(1);

        if (methodName.equals(favicon)) return;

        //调用泛化接口，将结果进行返回
        GatewaySession gatewaySession = gatewaySessionFactory.openSession();
        IGenericReference genericReference = gatewaySession.getMapper(uri);
        String result = genericReference.$invoke("test") + " " + System.currentTimeMillis();

        //返回信息控制
        response.content().writeBytes(JSON.toJSONBytes(result, SerializerFeature.PrettyFormat));
        //设置响应头
        HttpHeaders headers = response.headers();

        //配置响应头基础信息
        headers.add(HttpHeaderNames.CONTENT_TYPE,HttpHeaderValues.APPLICATION_JSON+"; charset=UTF-8");
        headers.add(HttpHeaderNames.CONTENT_LENGTH,response.content().readableBytes());
        headers.add(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);

        //配置跨域访问
        headers.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN,"*");
        headers.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS,"*");
        headers.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS,"POST, GET, PUT, DELETE");
        headers.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_CREDENTIALS,"true");

        channel.writeAndFlush(response);
    }
}
