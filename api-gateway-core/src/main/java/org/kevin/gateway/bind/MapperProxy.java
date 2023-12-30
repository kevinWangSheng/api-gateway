package org.kevin.gateway.bind;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.kevin.gateway.session.GatewaySession;

import java.lang.reflect.Method;

/**
 * @author wang
 * @create 2023-12-29-18:31
 */
public class MapperProxy implements MethodInterceptor {
    private GatewaySession gatewaySession;

    private String uri;

    public MapperProxy(GatewaySession gatewaySession, String uri) {
        this.gatewaySession = gatewaySession;
        this.uri = uri;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        MapperMethod linkedMethod = new MapperMethod(uri, gatewaySession.getConfiguration());
        return linkedMethod.execute(gatewaySession, args[0]);
    }


}
