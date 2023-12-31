package com.kevin.gateway.infrustructs.po;

import java.util.Date;

/**
 * @author wang
 * @create 2024-01-01-0:13
 */
public class HttpStatement {
    private Long id; // 主键Id
    private String application; // 应用程序名称

    private String interfaceName; // 接口名称

    private String methodName; // 方法名称

    private String parameterTypes; // 参数类型

    private String uri; // 请求路径

    private String httpCommandType; //请求方式 get、post
    
    private int auth; // 是否需要鉴权 1-true 0-false default 0

    private Date createTime; // 创建时间

    private Date upteTime; // 更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
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

    public Date getUpteTime() {
        return upteTime;
    }

    public void setUpteTime(Date upteTime) {
        this.upteTime = upteTime;
    }
}
