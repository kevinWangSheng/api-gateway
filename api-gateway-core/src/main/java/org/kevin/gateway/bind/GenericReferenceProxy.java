package org.kevin.gateway.bind;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.dubbo.rpc.service.GenericService;

import java.lang.reflect.Method;

/**
 * @author wang
 * @create 2023-12-28-22:06
 */
public class GenericReferenceProxy implements MethodInterceptor {
    private final GenericService genericService;

    private final String methodName;

    public GenericReferenceProxy(GenericService genericService, String methodName) {
        this.genericService = genericService;
        this.methodName = methodName;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] parameters = new String[parameterTypes.length];
        for(int i = 0;i<parameters.length;i++){
            parameters[i] = parameterTypes[i].getName();
        }

        return genericService.$invoke(methodName,parameters,objects);
    }
}
