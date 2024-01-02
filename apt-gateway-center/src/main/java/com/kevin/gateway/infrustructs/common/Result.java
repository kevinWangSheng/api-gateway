package com.kevin.gateway.infrustructs.common;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/** 统一返回对象中，Code码、Info描述
 * @author wang
 * @create 2024-01-02-15:57
 */
public class Result <T> implements Serializable {
    private final static long serialVersionUID = -3826891916021780628L;

    private String code;

    private String info;

    private T data;

    public Result() {
    }

    public Result(String code, String info, T data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
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
