package org.kevin.gateway.bind;

import org.kevin.gateway.mapping.HttpCommandType;
import org.kevin.gateway.mapping.HttpStatement;
import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.session.GatewaySession;

import java.util.Map;

/**
 * @author wang
 * @create 2023-12-29-17:42
 */
public class MapperMethod {
    private String methodName;

    private HttpCommandType commandType;

    public MapperMethod(String uri, Configuration configuration) {
        HttpStatement httpStatement = configuration.getHttpStatement(uri);
        this.methodName = httpStatement.getMethodName();
        this.commandType = httpStatement.getHttpCommandType();
    }

    public Object execute(GatewaySession session, Map<String,Object> params){
        Object result = null;
        switch (commandType){
            case GET:
                // 执行对应的请求操作
                result = session.get(methodName,params);
                break;
            case POST:
                result = session.post(methodName,params);
                break;
            case DELETE:
                break;
            case PUT:
                break;
            default:
                throw new RuntimeException("exception for the commandType,not in [post,get,put,delete]");
        }
        return result;
    }
}
