package com.kevin.gateway.infrustructs.po;

import java.io.Serializable;
import java.util.Date;

/**应用程序接口方法信息表
 * @author wang
 * @create 2024-01-02-15:47
 */
public class ApplicationInterfaceMethod implements Serializable {
    private final static long serialVersionUID = 1L;

    private long id; // 主键自增id

    private String systemId; // 系统id

    private String interfaceId; // 接口id

    private String methodId; // 方法id

    private String methodName; // 方法名称

    private String parameterTypes; // 参数类型 比如 java.lang.String

    private String uri; // http请求地址

    private String httpCommandType; // http请求类型 GET 或者POST

    private int auth; // 是否需要鉴权 0不需要 1需要

    private Date createTime; // 创建时间

    private Date updateTime; // 更新时间

    public ApplicationInterfaceMethod() {
    }

    public ApplicationInterfaceMethod(long id, String systemId, String interfaceId, String methodId, String methodName, String parameterType, String uri, String httpCommandType, int auth, Date createTime, Date updateTime) {
        this.id = id;
        this.systemId = systemId;
        this.interfaceId = interfaceId;
        this.methodId = methodId;
        this.methodName = methodName;
        this.parameterTypes = parameterType;
        this.uri = uri;
        this.httpCommandType = httpCommandType;
        this.auth = auth;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(String parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHttpCommandType() {
        return httpCommandType;
    }

    public void setHttpCommandType(String httpCommandType) {
        this.httpCommandType = httpCommandType;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
