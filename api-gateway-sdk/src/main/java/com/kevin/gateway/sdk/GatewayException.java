package com.kevin.gateway.sdk;

/**
 * @author wang
 * @create 2023-12-29-1:02
 */
public class GatewayException extends RuntimeException{
    public GatewayException(String message) {
        super(message);
    }

    public GatewayException(String message, Throwable cause) {
        super(message, cause);
    }
}
