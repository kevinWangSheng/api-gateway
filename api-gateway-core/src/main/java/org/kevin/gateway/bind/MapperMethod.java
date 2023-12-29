package org.kevin.gateway.bind;

import org.kevin.gateway.mapping.HttpCommandType;
import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.session.GatewaySession;

/**
 * @author wang
 * @create 2023-12-29-17:42
 */
public class MapperMethod {
    private String uri;

    private HttpCommandType commandType;

    public MapperMethod(String uri, Configuration configuration) {
        this.uri = uri;
        this.commandType = configuration.getHttpStatement(uri).getHttpCommandType();
    }

    public Object execute(GatewaySession session, Object args){
        Object result = null;
        switch (commandType){
            case GET:
                // 执行对应的请求操作
                result = session.get(uri,args);
                break;
            case POST:
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
