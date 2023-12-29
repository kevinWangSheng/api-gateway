package org.kevin.gateway.bind;

import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import org.kevin.gateway.mapping.HttpStatement;
import org.kevin.gateway.session.GatewaySession;
import org.objectweb.asm.Type;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @create 2023-12-29-18:35
 */
public class MapperProxyFactory {
    private String uri;

    public MapperProxyFactory(String uri) {
        this.uri = uri;
    }

    private final Map<String, IGenericReference> genericReferenceCache = new HashMap<>();
    public IGenericReference newInstance(GatewaySession gatewaySession){
        return genericReferenceCache.computeIfAbsent(uri ,k->{
            HttpStatement httpStatement = gatewaySession.getConfiguration().getHttpStatement(uri);

            MapperProxy genericReferenceProxy = new MapperProxy(gatewaySession,uri);

            // 创建执行动态代理的对象
            InterfaceMaker interfaceMaker = new InterfaceMaker();
            interfaceMaker.add(new Signature(httpStatement.getMethodName(), Type.getType(String.class),new Type[]{Type.getType(String.class)}),null);

            Class<?> interfaceClass = interfaceMaker.create();

            //设置代理对象
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Object.class);

            //要代理的接口
            enhancer.setInterfaces(new Class[]{IGenericReference.class,interfaceClass});
            enhancer.setCallback(genericReferenceProxy);
            return (IGenericReference) enhancer.create();
        });
    }
}
