package org.kevin.gateway.mapping;

/**
 * @author wang
 * @create 2023-12-29-17:40
 */
public class HttpStatement {
    private String uri;

    private String interfaceName;

    private String methodName;

    private String application;

    private HttpCommandType httpCommandType;

    public HttpStatement() {
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
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

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public HttpCommandType getHttpCommandType() {
        return httpCommandType;
    }

    public void setHttpCommandType(HttpCommandType httpCommandType) {
        this.httpCommandType = httpCommandType;
    }

    public HttpStatement(String uri, String interfaceName, String methodName, String application, HttpCommandType commandType) {
        this.uri = uri;
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.application = application;
        this.httpCommandType = commandType;
    }
}
