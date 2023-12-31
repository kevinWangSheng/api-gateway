package org.kevin.gateway.executor;

import org.kevin.gateway.datasource.Connection;
import org.kevin.gateway.executor.result.SessionResult;
import org.kevin.gateway.mapping.HttpStatement;
import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.type.SimpleTypeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author wang
 * @create 2023-12-30-22:02
 */
public abstract class BaseExecutor implements Executor{
    private final Logger logger = LoggerFactory.getLogger(BaseExecutor.class);

    protected Configuration configuration;

    protected Connection connection;

    public BaseExecutor(Configuration configuration, Connection connection) {
        this.configuration = configuration;
        this.connection = connection;
    }

    @Override
    public SessionResult exec(HttpStatement httpStatement, Map<String, Object> params) {
        String methodName = httpStatement.getMethodName();
        String parameterType = httpStatement.getParameterType();
        String[] paramtersTypes = new String[]{parameterType};
        Object[] args = SimpleTypeRegistry.isSimpleType(parameterType) ? params.values().toArray() : new Object[]{params};

        logger.info("执行调用：application:{},interface:{},method：{}，parameterType:{}",httpStatement.getApplication(),httpStatement.getInterfaceName(),methodName,parameterType);
        try {
            Object result = doExec(methodName, paramtersTypes, args);
            return SessionResult.buildSuccess(result);
        } catch (Exception e) {
            return SessionResult.buildFail(e.getMessage());
        }

    }

    protected abstract Object doExec(String methodName,String[] parameterTypes,Object[] args);
}
