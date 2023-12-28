package com.kevin.gateway.sdk.common;

/**
 * @author wang
 * @create 2023-12-29-0:51
 */
public class Result<T> {
    private String message;

    private int code;

    private T data;

    public Result() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
