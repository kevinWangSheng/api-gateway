package com.kevin.gateway.domain.manage.model.vo;

/**接口方法VO
 * @author wang
 * @create 2024-01-04-16:05
 */
public class ApplicationInterfaceMethodVO {
    private String systemId;

    private String interfaceId;

    private String methodId;

    private String methodName;

    private String parameterTypes;

    private String uri;

    private String httpCommandType;

    private Integer auth;

    public ApplicationInterfaceMethodVO() {
    }

    public ApplicationInterfaceMethodVO(String systemId, String interfaceId, String methodId, String methodName, String parameterTypes, String uri, String httpCommandType, Integer auth) {
        this.systemId = systemId;
        this.interfaceId = interfaceId;
        this.methodId = methodId;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.uri = uri;
        this.httpCommandType = httpCommandType;
        this.auth = auth;
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

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }
}
