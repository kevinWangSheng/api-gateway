package com.kevin.gateway.domain.model;

/**
 * @author wang
 * @create 2024-01-01-0:33
 */
public class ApiData {
    private String application; // 应用名称

    private String interfaceName; // 接口名称

    private String methodName; // 方法名称

    private String parameterType; // 参数类型

    private String uri; // 请求路径

    private String httpCommandType; // 请求方式 GET,POST

    private int auth; // 是否需要鉴权 1-true 0-false default 0

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

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
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
}
