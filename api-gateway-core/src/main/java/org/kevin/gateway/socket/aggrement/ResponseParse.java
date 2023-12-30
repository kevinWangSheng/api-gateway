package org.kevin.gateway.socket.aggrement;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.netty.handler.codec.http.*;

/**
 * @author wang
 * @create 2023-12-30-22:20
 */
public class ResponseParse {
    public DefaultFullHttpResponse parse(Object result){
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.content().writeBytes(JSON.toJSONBytes(result, SerializerFeature.PrettyFormat));
        //设置响应头
        HttpHeaders headers = response.headers();

        //配置响应头基础信息
        headers.add(HttpHeaderNames.CONTENT_TYPE,HttpHeaderValues.APPLICATION_JSON+"; charset=UTF-8");
        headers.add(HttpHeaderNames.CONTENT_LENGTH,response.content().readableBytes());
        headers.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);

        //配置跨域访问
        headers.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN,"*");
        headers.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS,"*");
        headers.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS,"POST, GET, PUT, DELETE");
        headers.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_CREDENTIALS,"true");
        return response;
    }
}
