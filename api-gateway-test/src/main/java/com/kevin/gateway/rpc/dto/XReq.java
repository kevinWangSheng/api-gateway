package com.kevin.gateway.rpc.dto;

/**
 * @author wang
 * @create 2023-12-28-23:15
 */
public class XReq {
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
