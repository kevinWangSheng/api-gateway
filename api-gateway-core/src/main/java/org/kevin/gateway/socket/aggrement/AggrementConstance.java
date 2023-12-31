package org.kevin.gateway.socket.aggrement;

import io.netty.util.AttributeKey;
import org.kevin.gateway.mapping.HttpStatement;

/**
 * @author wang
 * @create 2023-12-31-14:35
 */
public class AggrementConstance{
    public static final AttributeKey<HttpStatement> HTTP_STATEMENT = AttributeKey.valueOf("HttpStatement");

    public enum ResponseCode{

        _200("200","访问成功"),
        _400("400","接收数据的数据类型不匹配"),
        _403("403","服务器拒绝了你的请求"),
        _404("404","请求的资源不存在"),
        _500("500","服务器遇到错误，无法完成请求"),
        _502("502","服务器作为网关或代理，从上游服务器收到无效请求");

        private String code;

        private String info;

        ResponseCode(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
