package com.kevin.gateway.common;

/**
 * @author wang
 * @create 2024-01-02-21:23
 */
public class Result<T> {
    private String code;

    private String info;

    private T data;



    public Result() {
    }

    public Result(String code, String info) {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
